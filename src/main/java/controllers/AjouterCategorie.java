package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.sql.Date;
import java.time.LocalDate;

import model.categorie;
import service.IService;
import service.CategorieService;

import java.io.IOException;
import java.sql.SQLException;

public class AjouterCategorie {

    @FXML
    private TextField reference;

    @FXML
    private TextField nom;

    @FXML
    private TextArea description;

    @FXML
    private DatePicker datecreation;

    // Création de l'objet CategorieService
    CategorieService cs = new CategorieService();

    @FXML
    void addCategorie(ActionEvent event) {
        // Vérification si les champs sont remplis
        if (reference.getText().isEmpty() || nom.getText().isEmpty() || description.getText().isEmpty() || datecreation.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Tous les champs doivent être remplis !");
            alert.showAndWait();
            return;
        }

        try {
            // Conversion de la date
            LocalDate localDate = datecreation.getValue();
            Date sqlDate = Date.valueOf(localDate);

            // Création d'une nouvelle catégorie
            categorie newCategorie = new categorie(reference.getText(),
                    nom.getText(),
                    description.getText(),
                    sqlDate);

            // Ajout de la catégorie dans la base de données
            cs.add(newCategorie);

            // Message de succès
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setContentText("Catégorie ajoutée avec succès !");
            alert.showAndWait();

            // Réinitialisation des champs
            reference.clear();
            nom.clear();
            description.clear();
            datecreation.setValue(null);

        } catch (Exception e) {
            // Gestion générale des erreurs (y compris SQLException)
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Il y a eu une erreur lors de l'ajout de la catégorie.");
            alert.showAndWait();
        }
    }

    @FXML
    void listCategorie(ActionEvent event) {
        try {
            // Charger la scène de la liste des catégories
            Parent root = FXMLLoader.load(getClass().getResource("/ToutesLesCategories.fxml"));

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Récupérer la fenêtre actuelle (Stage) et changer la scène
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            // Gestion de l'exception en cas de problème lors du chargement de la scène
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Chargement");
            alert.setContentText("Il y a eu une erreur lors du chargement de la liste des catégories.");
            alert.showAndWait();
        }
    }
    @FXML
    void listprodctg(ActionEvent event) {
        try {
            // Charger le fichier FXML de la nouvelle scène
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TousLesProduits.fxml"));

            // Créer la nouvelle scène
            Parent root = loader.load();

            // Obtenir la fenêtre actuelle (Stage)
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Changer la scène avec la nouvelle
            currentStage.setScene(new Scene(root));

            // Afficher la nouvelle scène
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Vous pouvez afficher une alerte si le fichier FXML n'est pas trouvé ou si une erreur se produit
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Problème de chargement");
            alert.setContentText("Une erreur est survenue lors du chargement de la page.");
            alert.showAndWait();
        }
    }

}
