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
import service.CategorieService;
import model.categorie;

import java.io.File;
import java.util.List;

public class UpdateProduit {
    private ObservableList<produit> produits = FXCollections.observableArrayList();

    @FXML
    private ImageView imgviewupdtProd;

    @FXML
    private ComboBox<String> categoriefComboBox;

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

        // Charger toutes les catégories dans le ComboBox
        loadCategories();

        // Sélectionner la catégorie actuelle du produit dans le ComboBox
        categoriefComboBox.setValue(getCategorieNameById(product.getIdCategorie()));
    }

    // Méthode pour charger toutes les catégories dans le ComboBox
    private void loadCategories() {
        CategorieService categorieService = new CategorieService();
        List<categorie> categories = categorieService.display();

        // Extraire le nom de chaque catégorie et les ajouter au ComboBox
        ObservableList<String> categoryNames = FXCollections.observableArrayList();
        for (categorie cat : categories) {
            categoryNames.add(cat.getNom());
        }

        categoriefComboBox.setItems(categoryNames);  // Assigner les catégories au ComboBox
    }


    @FXML
    void updateProduit(ActionEvent event) {
        try {
            // Vérification des champs obligatoires
            if (nomf.getText().isEmpty() || prixf.getText().isEmpty() || descriptionf.getText().isEmpty() ||
                    imagepathf.getText().isEmpty() || marquef.getText().isEmpty() || referencef.getText().isEmpty() ||
                    couleursf.getSelectionModel().isEmpty() || statusf.getSelectionModel().isEmpty()) {

                showError("Champs Obligatoires", "Tous les champs doivent être remplis.");
                return;
            }

            // Validation du prix (doit être > 0)
            double prix;
            try {
                prix = Double.parseDouble(prixf.getText());
                if (prix <= 0) {
                    showError("Prix invalide", "Le prix doit être supérieur à 0.");
                    return;
                }
            } catch (NumberFormatException e) {
                showError("Prix invalide", "Veuillez entrer un prix valide.");
                return;
            }
            currentProduct.setPrix(prix);

            // Validation de l'ID de catégorie
            int idCategorie = getCategorieIdByName(categoriefComboBox.getValue());
            if (idCategorie == -1) {
                showError("ID de catégorie invalide", "Veuillez entrer une catégorie valide.");
                return;
            }

            // Mise à jour des propriétés du produit
            currentProduct.setNom(nomf.getText());
            currentProduct.setDescription(descriptionf.getText());
            currentProduct.setImagepath(imagepathf.getText());
            currentProduct.setMarque(marquef.getText());
            currentProduct.setReference(referencef.getText());
            currentProduct.setStatus(statusf.getSelectionModel().getSelectedItem());
            currentProduct.setCouleurs(couleursf.getSelectionModel().getSelectedItem());
            currentProduct.setIdCategorie(idCategorie);

            // Validation du stock (doit être >= 0)
            int stock;
            try {
                stock = Integer.parseInt(stockf.getText());
                if (stock < 0) {
                    showError("Stock invalide", "Le stock doit être supérieur ou égal à 0.");
                    return;
                }
            } catch (NumberFormatException e) {
                showError("Stock invalide", "Veuillez entrer un nombre valide pour le stock.");
                return;
            }
            currentProduct.setStock(stock);

            // Mise à jour du produit dans la base de données
            ProduitService produitService = new ProduitService();
            produitService.update(currentProduct);

            // Afficher un message de confirmation
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mise à jour réussie");
            alert.setHeaderText("Produit mis à jour avec succès");
            alert.setContentText("Les informations du produit ont été mises à jour.");
            alert.showAndWait();

            // Récupérer le Stage actuel et mettre à jour la liste des produits
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
            showError("Erreur de mise à jour", "Une erreur s'est produite lors de la mise à jour du produit.");
        }
    }



    // Affichage d'une fenêtre d'erreur
    private void showError(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
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

    // Méthode pour obtenir le nom de la catégorie par son ID
    private String getCategorieNameById(int idCategorie) {
        CategorieService categorieService = new CategorieService();
        List<categorie> categories = categorieService.display();
        for (categorie cat : categories) {
            if (cat.getIdCategorie() == idCategorie) {
                return cat.getNom();
            }
        }
        return null;
    }

    // Méthode pour obtenir l'ID de la catégorie par son nom
    private int getCategorieIdByName(String nomCategorie) {
        CategorieService categorieService = new CategorieService();
        List<categorie> categories = categorieService.display();
        for (categorie cat : categories) {
            if (cat.getNom().equals(nomCategorie)) {
                return cat.getIdCategorie();
            }
        }
        return -1; // Retourne -1 si la catégorie n'existe pas
    }
}
