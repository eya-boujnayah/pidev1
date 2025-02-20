package controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ProduitService;
import model.produit;

public class UpdateProduit {
    private ObservableList<produit> produits = FXCollections.observableArrayList();



    @FXML
    private TextField categorief;

    @FXML
    private TextField couleursf;

    @FXML
    private TextArea descriptionf;

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
    private TextField statusf;

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
        statusf.setText(product.getStatus());
        stockf.setText(String.valueOf(product.getStock()));
        couleursf.setText(product.getCouleurs());
    }
    @FXML
    void updateProduit(ActionEvent event) {
        // Mettre à jour le produit avec les nouvelles valeurs
        currentProduct.setNom(nomf.getText());
        currentProduct.setPrix(Double.parseDouble(prixf.getText()));
        currentProduct.setIdCategorie(Integer.parseInt(categorief.getText()));
        currentProduct.setDescription(descriptionf.getText());
        currentProduct.setImagepath(imagepathf.getText());
        currentProduct.setMarque(marquef.getText());
        currentProduct.setReference(referencef.getText());
        currentProduct.setStatus(statusf.getText());
        currentProduct.setStock(Integer.parseInt(stockf.getText()));
        currentProduct.setCouleurs(couleursf.getText());

        // Appel au service pour mettre à jour le produit dans la base de données
        ProduitService produitService = new ProduitService();
        produitService.update(currentProduct);

        // Afficher un message de confirmation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mise à jour réussie");
        alert.setHeaderText("Produit mis à jour avec succès");
        alert.setContentText("Les informations du produit ont été mises à jour.");
        alert.showAndWait();

        // Rafraîchir la liste dans TousLesProduits
        Stage stage = null;
        TousLesProduits mainController = (TousLesProduits) stage.getOwner().getUserData();
        Platform.runLater(() -> mainController.refreshList());

        // Fermer la fenêtre actuelle après mise à jour
        stage = (Stage) updateProduit.getScene().getWindow();
        stage.close();
    }
    @FXML
    void AnnulerUpdate(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }




    }

