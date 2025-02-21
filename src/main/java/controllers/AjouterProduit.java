package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.categorie;
import model.produit;
import service.CategorieService;
import service.IService;
import service.ProduitService;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AjouterProduit {

    @FXML
    private ComboBox<String> categorieComboBox;

    @FXML
    private ComboBox<String> couleurs;
    @FXML
    private ComboBox<String> status;
    @FXML
    private TextArea description;

    @FXML
    private TextField imagepath;

    @FXML
    private ImageView imageView;  // ImageView pour afficher l'image

    @FXML
    private TextField marque;

    @FXML
    private TextField nom;

    @FXML
    private TextField prix;

    @FXML
    private TextField reference;


    @FXML
    private TextField stock;

    private IService ps = new ProduitService();

    @FXML
    void addProduit(ActionEvent event) throws SQLException {
        // Vérification des champs obligatoires
        if (reference.getText().isEmpty() || nom.getText().isEmpty() || description.getText().isEmpty() ||
                prix.getText().isEmpty() || marque.getText().isEmpty() || stock.getText().isEmpty() ||
                couleurs.getSelectionModel().isEmpty() || status.getSelectionModel().isEmpty()) {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setContentText("Tous les champs sont obligatoires.");
            alert.showAndWait();
            return;
        }

        // Vérification du prix (doit être > 0)
        double prixValue;
        try {
            prixValue = Double.parseDouble(prix.getText());
            if (prixValue <= 0) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur de Saisie");
                alert.setContentText("Le prix doit être supérieur à 0.");
                alert.showAndWait();
                return;
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setContentText("Veuillez entrer un prix valide.");
            alert.showAndWait();
            return;
        }

        // Vérification du stock (doit être >= 0)
        int stockValue;
        try {
            stockValue = Integer.parseInt(stock.getText());
            if (stockValue < 0) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur de Saisie");
                alert.setContentText("Le stock doit être supérieur ou égal à 0.");
                alert.showAndWait();
                return;
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setContentText("Veuillez entrer un stock valide.");
            alert.showAndWait();
            return;
        }

        // Validation de la catégorie
        int idCategorie = getCategorieIdByName(categorieComboBox.getValue());
        if (idCategorie == -1) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur Catégorie");
            alert.setContentText("La catégorie sélectionnée est invalide.");
            alert.showAndWait();
            return;
        }

        // Ajouter le produit
        ps.add(new produit(reference.getText(),
                nom.getText(),
                description.getText(),
                prixValue,
                marque.getText(),
                stockValue,
                couleurs.getSelectionModel().getSelectedItem(),
                status.getSelectionModel().getSelectedItem(),
                imagepath.getText(),
                idCategorie));

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Succès");
        alert.setContentText("Produit ajouté avec succès.");
        alert.showAndWait();

        // Réinitialiser les champs
        reference.clear();
        nom.clear();
        description.clear();
        prix.clear();
        marque.clear();
        stock.clear();
        couleurs.getSelectionModel().clearSelection();
        status.getSelectionModel().clearSelection();
        imagepath.clear();
        imageView.setImage(null);  // Réinitialiser l'image affichée
    }


    @FXML
    void listproduit(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/TousLesProduits.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de Chargement");
            alert.setContentText("Il y a eu une erreur lors du chargement de la liste des produits.");
            alert.showAndWait();
        }
    }

    @FXML
    void ListCagProd(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterCategorie.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            AjouterCategorie ajouterCategorieController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de Chargement");
            alert.setContentText("Il y a eu une erreur lors du chargement de l'interface AjouterCategorie.");
            alert.showAndWait();
        }
    }

    @FXML
    void chooseImage(ActionEvent event) {
        // Ouvrir une boîte de dialogue pour choisir un fichier image
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Ouvrir la boîte de dialogue et obtenir le fichier sélectionné
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            // Récupérer le chemin correct du fichier
            String filePath = file.toURI().getPath();

            // Remplacer les barres obliques inverses par des barres obliques
            filePath = filePath.replace("\\", "/");

            // Mettre à jour le champ imagepath avec le chemin corrigé
            imagepath.setText(filePath);

            // Charger et afficher l'image dans ImageView
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
        } else {
            // Afficher un message d'erreur si aucun fichier n'est sélectionné
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aucune image sélectionnée");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une image.");
            alert.showAndWait();
        }
    }
    @FXML
    public void initialize() {
        loadCategories();
    }

    private void loadCategories() {
        CategorieService categorieService = new CategorieService();
        List<categorie> categories = categorieService.display();
        for (categorie cat : categories) {
            categorieComboBox.getItems().add(cat.getNom());
        }
    }
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

