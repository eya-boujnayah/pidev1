package controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.ProduitService;
import model.produit;

import java.io.File;
import javax.swing.*;


public class UpdateProduit {
    private ObservableList<produit> produits = FXCollections.observableArrayList();

    @FXML
    private ImageView imgviewupdtProd;


    @FXML
    private TextField categorief;



    @FXML
    private TextArea descriptionf;
    @FXML
    private ComboBox<String> couleursf;

    @FXML
    private TextField imagepathf;

    @FXML
    private TextField marquef;

    @FXML
    private TextField nomf;

    @FXML
    private TextField prixf;

    @FXML
    private TextField referencef;
    @FXML
    private ComboBox<String> statusf;

    @FXML
    private TextField stockf;

    @FXML
    private Button updateProduit;

    private produit currentProduct;  // Le produit à mettre à jour

    // Méthode pour définir le produit actuel dans le contrôleur
    public void setUpdateProduit(produit product) {
        this.currentProduct = product;

        // Remplir les champs avec les données du produit actuel
        nomf.setText(product.getNom());
        prixf.setText(String.valueOf(product.getPrix()));
        categorief.setText(String.valueOf(product.getIdCategorie()));  // Si 'getNom' existe dans la classe 'Categorie'
        descriptionf.setText(product.getDescription());
        imagepathf.setText(product.getImagepath());
        marquef.setText(product.getMarque());
        referencef.setText(product.getReference());
        statusf.setValue(product.getStatus());
        stockf.setText(String.valueOf(product.getStock()));
        couleursf.setValue(product.getCouleurs());

        // Charger l'image de l'ancienne image dans l'ImageView
        String imagePath = product.getImagepath();
        if (imagePath != null && !imagePath.isEmpty()) {
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                Image image = new Image(imageFile.toURI().toString());
                imgviewupdtProd.setImage(image);  // Afficher l'image dans l'ImageView
            }
        }
    }
    @FXML
    void updateProduit(ActionEvent event) {
        try {
            // Mettre à jour le produit avec les nouvelles valeurs
            currentProduct.setNom(nomf.getText());
            currentProduct.setPrix(Double.parseDouble(prixf.getText()));
            currentProduct.setIdCategorie(Integer.parseInt(categorief.getText()));
            currentProduct.setDescription(descriptionf.getText());
            currentProduct.setImagepath(imagepathf.getText());
            currentProduct.setMarque(marquef.getText());
            currentProduct.setReference(referencef.getText());
            currentProduct.setStatus(statusf.getSelectionModel().getSelectedItem().toString());
            currentProduct.setStock(Integer.parseInt(stockf.getText()));
            currentProduct.setCouleurs(couleursf.getSelectionModel().getSelectedItem().toString());


            // Appel au service pour mettre à jour le produit dans la base de données
            ProduitService produitService = new ProduitService();
            produitService.update(currentProduct);

            // Afficher un message de confirmation
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mise à jour réussie");
            alert.setHeaderText("Produit mis à jour avec succès");
            alert.setContentText("Les informations du produit ont été mises à jour.");
            alert.showAndWait();

            // Récupérer le Stage actuel
            Stage stage = (Stage) updateProduit.getScene().getWindow();
            if (stage != null && stage.getOwner() != null) {
                TousLesProduits mainController = (TousLesProduits) stage.getOwner().getUserData();
                if (mainController != null) {
                    Platform.runLater(mainController::refreshList);
                }
            }

            // Fermer la fenêtre actuelle après mise à jour
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Échec de la mise à jour");
            alert.setContentText("Vérifiez les données saisies.");
            alert.showAndWait();
        }
    }

    @FXML
    void AnnulerUpdate(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @FXML
    private void chooseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);  // Ouvre la boîte de dialogue pour choisir un fichier

        if (selectedFile != null) {
            // Si un fichier est sélectionné, met à jour le chemin dans le TextField
            imagepathf.setText(selectedFile.getAbsolutePath());

            // Mettre à jour l'image dans l'ImageView
            String imagePath = selectedFile.getAbsolutePath();
            Image image = new Image(imagePath);  // Charger l'image
            imgviewupdtProd.setImage(image);  // Afficher l'image dans l'ImageView
        }
    }

}

