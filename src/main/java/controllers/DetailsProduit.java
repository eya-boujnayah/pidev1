package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.categorie;
import model.produit;
import service.CategorieService;
import service.ProduitService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DetailsProduit {

    @FXML private Label labelReference;
    @FXML private Label labelNomProduit;
    @FXML private Label labelDescriptionProduit;
    @FXML private Label labelMarque;
    @FXML private Label labelPrixProduit;
    @FXML private Label labelStock;
    @FXML private Label labelCouleurs;
    @FXML private Label labelStatus;
    @FXML private Label labelIdCategorie;
    @FXML private ImageView imageView; // ImageView pour afficher l'image du produit

    private static produit produitActuel;  // Attribut privé

    private ProduitService produitService = new ProduitService(); // Création d'une instance de ProduitService

    // Ajouter l'attribut CategorieService
    CategorieService categorieService = new CategorieService();

    // Méthode pour définir les détails du produit
    public void setProduit(produit produit) {
        this.produitActuel = produit;
        labelReference.setText("Référence : " + produit.getReference());
        labelNomProduit.setText("Nom : " + produit.getNom());
        labelDescriptionProduit.setText("Description : " + produit.getDescription());
        labelMarque.setText("Marque : " + produit.getMarque());
        labelPrixProduit.setText("Prix : " + produit.getPrix() + "Dt");
        labelStock.setText("Stock : " + produit.getStock());
        labelCouleurs.setText("Couleurs : " + produit.getCouleurs());
        labelStatus.setText("Statut : " + produit.getStatus());

        // Récupérer la catégorie et afficher son nom
        categorie cat = categorieService.getCategorieById(produit.getIdCategorie());
        labelIdCategorie.setText(" Catégorie : " + (cat != null ? cat.getNom() : "Inconnu"));

        // Ajouter l'affichage de l'image si le path est valide
        afficherImage(produit.getImagepath());
    }


    // Méthode pour afficher l'image du produit
    private void afficherImage(String imagePath) {
        try {
            File file = new File(imagePath);
            if (file.exists()) {
                // Charger l'image depuis le chemin du fichier
                Image image = new Image(new FileInputStream(file));
                imageView.setImage(image);
            } else {
                // Afficher une image par défaut si le fichier n'est pas trouvé
                Image defaultImage = new Image("default-image-path.png"); // Remplacer par le chemin de votre image par défaut
                imageView.setImage(defaultImage);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // Afficher une image par défaut si une exception est lancée
            Image defaultImage = new Image("default-image-path.png"); // Remplacer par le chemin de votre image par défaut
            imageView.setImage(defaultImage);
        }
    }

    // Fermer la fenêtre actuelle
    @FXML
    private void fermerFenetre() {
        Stage stage = (Stage) labelNomProduit.getScene().getWindow();
        stage.close();
    }

    @FXML
    void updtprd(ActionEvent event) {
        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateProduit.fxml"));
            Scene detailsScene = new Scene(loader.load());
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(detailsScene);
            currentStage.show();
            // Récupérer le contrôleur
            UpdateProduit controller = loader.getController();

            controller.setUpdateProduit(produitActuel);
        } catch (Exception e) {
            System.out.println("Erreur inattendue : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
