package controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.categorie; // Assurez-vous d'avoir un modèle Categorie
import service.CategorieService; // Service pour gérer les catégories
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class ToutesLesCategories {

    @FXML
    private ListView<categorie> listViewCategories;

    private final CategorieService categorieService = new CategorieService();
    private ObservableList<categorie> categories;

    @FXML
    public void initialize() {
        // Initialiser l'ObservableList avec les catégories existantes
        categories = FXCollections.observableArrayList(categorieService.display());
        listViewCategories.setItems(categories);

        // Personnaliser l'affichage de chaque élément de la liste
        listViewCategories.setCellFactory(param -> new ListCell<categorie>() {
            @Override
            protected void updateItem(categorie categorie, boolean empty) {
                super.updateItem(categorie, empty);
                if (empty || categorie == null) {
                    setGraphic(null);
                } else {
                    Text categoryText = new Text(categorie.getNom() + " : " + categorie.getDescription());
                    Region spacer = new Region();
                    HBox.setHgrow(spacer, Priority.ALWAYS);

                    Button detailsButton = new Button("Détails");
                    detailsButton.setStyle("-fx-font-size: 12px; -fx-padding: 5px 0px;");
                    detailsButton.setPrefSize(80, 30);
                    detailsButton.setOnAction(event -> afficherDetails(categorie));

                    Button deleteButton = new Button("Supprimer");
                    deleteButton.setStyle("-fx-font-size: 12px; -fx-padding: 5px 0px; -fx-background-color: red; -fx-text-fill: white;");
                    deleteButton.setPrefSize(80, 30);
                    deleteButton.setOnAction(event -> deleteCategorie(categorie));

                    HBox buttonContainer = new HBox(10, detailsButton, deleteButton);
                    buttonContainer.setMaxWidth(Double.MAX_VALUE);
                    buttonContainer.setStyle("-fx-alignment: center-right;");

                    VBox vbox = new VBox(5, categoryText, buttonContainer);
                    vbox.setStyle("-fx-padding: 10px;");

                    setGraphic(vbox);
                }
            }
        });
        // Rafraîchir la liste toutes les 10 secondes
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> refreshList()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void afficherDetails(categorie categorie) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DetailsCategorie.fxml"));
            Scene detailsScene = new Scene(loader.load());
            DetailsCategorie controller = loader.getController();
           controller.setCategorie(categorie);
            Stage detailsStage = new Stage();
            detailsStage.setTitle("Détails de la catégorie");
            detailsStage.setScene(detailsScene);
            detailsStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer une catégorie et mettre à jour la ListView
    @FXML
    void deleteCategorie(categorie categorie) {
        if (categorie != null) {
            categorieService.delete(categorie.getIdCategorie());
            Platform.runLater(() -> {
                categories.remove(categorie); // Suppression de la catégorie directement dans la liste observable
            });
        }
    }

    // Méthode pour ajouter une catégorie et mettre à jour la ListView
    @FXML
    void addCategorie(categorie categorie) {
        if (categorie != null) {
            categorieService.add(categorie); // Ajout en base de données
            Platform.runLater(() -> {
                categories.add(categorie); // Ajout de la catégorie dans la liste observable
            });
        }
    }

    @FXML
    void ReturnToAddCategorie(ActionEvent event) {
        try {
            // Charger la scène de la page d'ajout de catégorie
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterCategorie.fxml"));
            Scene sceneAjout = new Scene(loader.load());

            // Récupérer la fenêtre actuelle
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow(); // Utilisation de l'événement pour récupérer le bouton
            stage.setScene(sceneAjout); // Changer la scène
            stage.show(); // Afficher la nouvelle scène
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshList() {
        categories.setAll(categorieService.display()); // Recharge les catégories
    }
}
