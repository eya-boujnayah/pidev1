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

    public void setCategorieById(int idCategorie) {
        this.categorieActuelle = categorieService.getCategorieById(idCategorie);

        if (categorieActuelle != null) {
            labelReference.setText("Référence : " + categorieActuelle.getReference());
            labelNomCategorie.setText("Nom : " + categorieActuelle.getNom());
            labelDescriptionCategorie.setText("Description : " + categorieActuelle.getDescription());
            labelDateCreation.setText("Date de création : " + categorieActuelle.getDateCreation().toString());
        } else {
            labelNomCategorie.setText("Catégorie introuvable");
        }
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
