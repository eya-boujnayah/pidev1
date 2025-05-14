package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.produit;
import model.Utilisateur;
import service.PanierService;
import service.ProduitService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

public class PanierController {

    @FXML
    private VBox vboxPanier;
    @FXML
    private Label labelTotal;
    @FXML
    private Button btnValider;

    private PanierService panierService = new PanierService();
    private ProduitService produitService = new ProduitService();

    @FXML
    private TextField adresseLivraison;  // Nouveau champ pour l'adresse de livraison
    @FXML
    private ComboBox<String> typePaiement;

    public PanierController() throws SQLException {
    }

    @FXML
    public void initialize() {
        afficherPanier();
    }

    private void afficherPanier() {
        vboxPanier.getChildren().clear();
        double totalPanier = 0;

        for (Map.Entry<produit, Integer> entry : panierService.getPanier().entrySet()) {
            produit prod = entry.getKey();
            int quantite = entry.getValue();
            double prixTotal = prod.getPrix() * quantite;
            totalPanier += prixTotal;

            HBox hboxArticle = new HBox(10);
            hboxArticle.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-border-radius: 5px; -fx-border-color: #ccc;");



            Label lblNom = new Label(quantite + " x " + prod.getNom());
            lblNom.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

            Label lblPrix = new Label(prixTotal + "€");
            lblPrix.setStyle("-fx-font-size: 14px; -fx-text-fill: #e67e22;");

            Button btnSupprimer = new Button("❌");
            btnSupprimer.setStyle("-fx-background-color: transparent; -fx-font-size: 14px;");
            btnSupprimer.setOnAction(e -> {
                panierService.supprimerDuPanier(prod.getIdProduit());
                afficherPanier(); // Mettre à jour l'affichage après suppression
            });

            hboxArticle.getChildren().addAll( lblNom, lblPrix, btnSupprimer);
            vboxPanier.getChildren().add(hboxArticle);
        }

        // Mise à jour du total
        labelTotal.setText("Total : " + panierService.getTotalPanier() + "€");
    }

    @FXML
    private void fermerPanier() {
        Stage stage = (Stage) vboxPanier.getScene().getWindow();
        stage.close();
    }
//    @FXML
//    private void validerCommande() {
//        if (panierService.getPanier().isEmpty()) {
//            System.out.println("❌ Le panier est vide. Impossible de valider la commande !");
//            return;
//        }
//
//        utilisateur utilisateurConnecte = new utilisateur(1);
//        float prix = (float) panierService.getTotalPanier();
//        Date date= new Date();
//        model.Commande nouvelleCommande = new model.Commande(utilisateurConnecte, prix, "Confirmée", date );
//
//        CommandeService commandeService = new CommandeService();
//        commandeService.addcommande(nouvelleCommande, panierService.getPanier());
//
//   panierService.viderPanier();
//        afficherPanier();
//
//        System.out.println("✅ Commande validée avec succès !");
//    }

//    @FXML
//    private void validerCommande() throws SQLException {
//        if (panierService.getPanier().isEmpty()) {
//            System.out.println("❌ Le panier est vide. Impossible de valider la commande !");
//            return;
//        }
//
//        // Récupération des informations saisies par l'utilisateur
//        String adresse = adresseLivraison.getText();
//        String paiement = typePaiement.getValue();
//
//        if (adresse.isEmpty() || paiement == null) {
//            System.out.println("❌ Veuillez saisir l'adresse de livraison et choisir le type de paiement.");
//            return;
//        }
//
//        utilisateur utilisateurConnecte = new utilisateur(1);  // Utilisateur connecté, ici un exemple
//        float prix = (float) panierService.getTotalPanier();
//        Date date = new Date();
//        model.Commande nouvelleCommande = new model.Commande(utilisateurConnecte, prix, "Confirmée", date);
//
//        CommandeService commandeService = new CommandeService();
//        commandeService.addcommande(nouvelleCommande, panierService.getPanier());
//
////        // Création de la facture
////        Facture facture = new Facture(nouvelleCommande, utilisateurConnecte, prix, paiement, adresse, date, "Non payée");
////        FactureService factureService = new FactureService();
////        factureService.add(facture);
//
//        // Vider le panier après la commande
//       // panierService.viderPanier();
//       // afficherPanier();
//        ouvrirFactureInterface(nouvelleCommande);
//
//        System.out.println("✅ Commande validée et facture créée avec succès !");
//    }


    @FXML
    private void validerCommande() throws SQLException, IOException {
        if (panierService.getPanier().isEmpty()) {
            System.out.println("❌ Le panier est vide. Impossible de valider la commande !");
            return;
        }



        Utilisateur utilisateurConnecte = new Utilisateur(3);  // Utilisateur connecté, ici un exemple
        float prix = (float) panierService.getTotalPanier();
        Date date = new Date();
        model.Commande nouvelleCommande = new model.Commande(utilisateurConnecte, prix, "Confirmée", date);

        panierService.addcommande(nouvelleCommande, panierService.getPanier());
        System.out.println("ID Commande: " + nouvelleCommande.getIdCommande());

        // Vider le panier après la commande
        panierService.viderPanier();

        // Ouvrir l'interface de facture
        ouvrirFactureInterface(nouvelleCommande);

        System.out.println("✅ Commande validée et facture créée avec succès !");
    }

    private void ouvrirFactureInterface(model.Commande commande) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ValidFacture.fxml"));
        Parent factureRoot = loader.load();

// Récupérer le contrôleur associé
        FactureController factureController = loader.getController();
        factureController.setCommande(commande);

        Stage stage = (Stage) btnValider.getScene().getWindow();
        stage.setScene(new Scene(factureRoot));
    }


    public void backH(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Home.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
