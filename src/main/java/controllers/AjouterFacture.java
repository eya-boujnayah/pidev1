package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Commande;
import model.Facture;
import model.Utilisateur;
import service.FactureService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class AjouterFacture {

    @FXML
    private ChoiceBox<Integer> idUtilisateur;
    @FXML
    private ChoiceBox<Integer> idCommande;
    @FXML
    private TextField prixTotal;
    @FXML
    private TextField typePaiement;
    @FXML
    private TextField adresseLivraison;
    @FXML
    private TextField statutFacture;
    @FXML
    private DatePicker dateFacture;

    @FXML
    private Button back;

    private final FactureService factureService = new FactureService();

    public AjouterFacture() throws SQLException {
    }

    @FXML
    public void initialize() {
        loadUtilisateurs();
        loadCommandes();
    }

    private void loadUtilisateurs() {
        List<Integer> utilisateurs = factureService.getAllUtilisateurs();
        idUtilisateur.getItems().addAll(utilisateurs);
    }

    private void loadCommandes() {
        List<Integer> commandes = factureService.getAllCommandes();
        idCommande.getItems().addAll(commandes);
    }

    @FXML
    public void AjouterFacture(ActionEvent actionEvent) {
        try {
            if (idUtilisateur.getValue() == null || idCommande.getValue() == null ||
                    prixTotal.getText().isEmpty() || typePaiement.getText().isEmpty() ||
                    adresseLivraison.getText().isEmpty() || statutFacture.getText().isEmpty() ||
                    dateFacture.getValue() == null) {

                showAlert("Erreur", "Veuillez remplir tous les champs.");
                return;
            }

            // Récupérer les données du formulaire
            int utilisateurId = idUtilisateur.getValue();
            int commandeId = idCommande.getValue();
            float prix = Float.parseFloat(prixTotal.getText());
            String paiement = typePaiement.getText().trim();
            String adresse = adresseLivraison.getText().trim();
            String statut = statutFacture.getText().trim();
            LocalDate date = dateFacture.getValue();

            // Créer l'objet Commande
            Commande commande = new Commande();
            commande.setIdCommande(commandeId);

            // Créer l'objet Utilisateur
            Utilisateur user = new Utilisateur();
            user.setIdUtilisateur(utilisateurId);

            // Créer l'objet Facture
            Facture facture = new Facture();
            facture.setCommande(commande);
            facture.setUtilisateur(user);
            facture.setPrixTotal(prix);
            facture.setTypePaiement(paiement);
            facture.setAdresseLivraison(adresse);
            facture.setDateFacture(java.sql.Date.valueOf(date));
            facture.setStatutFacture(statut);

            // Ajouter la facture à la base de données
            factureService.add(facture);
            showAlert("Succès", "Facture ajoutée avec succès !");
            clearFields();

        } catch (Exception e) {
            showAlert("Erreur", "Une erreur est survenue : " + e.getMessage());
        }
    }

    @FXML
    public void AnnulerFacture(ActionEvent actionEvent) {
        clearFields();
    }

    @FXML
    public void back(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML Commande.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Factures.fxml"));
            AnchorPane root = loader.load();

            // Créer une nouvelle scène avec le FXML chargé
            Scene scene = new Scene(root);

            // Récupérer la scène actuelle
            Stage currentStage = (Stage) back.getScene().getWindow();

            // Définir la nouvelle scène dans la fenêtre principale
            currentStage.setScene(scene);
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible de charger l'interface des commandes !");
        }
    }

    private void clearFields() {
        prixTotal.clear();
        typePaiement.clear();
        adresseLivraison.clear();
        statutFacture.clear();
        dateFacture.setValue(null);
        idUtilisateur.setValue(null);
        idCommande.setValue(null);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
