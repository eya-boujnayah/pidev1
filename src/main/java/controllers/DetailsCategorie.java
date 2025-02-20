package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.categorie;
import service.CategorieService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Node;

import java.io.IOException;

public class DetailsCategorie {

    @FXML private Label labelReference;
    @FXML private Label labelNomCategorie;
    @FXML private Label labelDescriptionCategorie;
    @FXML private Label labelDateCreation;

    private static categorie categorieActuelle;  // Attribut privé

    private CategorieService categorieService = new CategorieService(); // Création d'une instance de CategorieService

    // Méthode pour définir les détails de la catégorie
    public void setCategorie(categorie categorie) {
        this.categorieActuelle = categorie;
        labelReference.setText("Référence : " + categorie.getReference());
        labelNomCategorie.setText("Nom : " + categorie.getNom());
        labelDescriptionCategorie.setText("Description : " + categorie.getDescription());
        labelDateCreation.setText("Date de création : " + categorie.getDateCreation().toString());
    }

    // Fermer la fenêtre actuelle
    @FXML
    private void fermerFenetre() {
        Stage stage = (Stage) labelNomCategorie.getScene().getWindow();
        stage.close();
    }

    @FXML
    void updtcat(ActionEvent event) {
       try {
            // Charger le fichier FXML pour la mise à jour
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateCategorie.fxml"));
            Scene detailsScene = new Scene(loader.load());
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(detailsScene);
            currentStage.show();

            // Récupérer le contrôleur pour la mise à jour
            UpdateCategorie controller = loader.getController();
            controller.setUpdateCategorie(categorieActuelle);

        } catch (IOException e) {
            System.out.println("Erreur inattendue : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
