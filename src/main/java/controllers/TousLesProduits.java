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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.produit;
import service.ProduitService;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TousLesProduits {

    @FXML
    private ListView<produit> listViewProduits;

    private final ProduitService produitService = new ProduitService();
    private ObservableList<produit> produits;

    @FXML
    public void initialize() {
        // Initialiser l'ObservableList avec les produits existants
        produits = FXCollections.observableArrayList(produitService.display());
        listViewProduits.setItems(produits);

        // Personnaliser l'affichage de chaque élément de la liste
        listViewProduits.setCellFactory(param -> new ListCell<produit>() {
            @Override
            protected void updateItem(produit produit, boolean empty) {
                super.updateItem(produit, empty);
                if (empty || produit == null) {
                    setGraphic(null);
                } else {
                    // Créer un objet Text pour afficher le nom et la description
                    Text productText = new Text(produit.getNom() + " : " + produit.getDescription());

                    // Créer un ImageView pour afficher l'image du produit
                    ImageView imageView = new ImageView();
                    try {
                        // Charger l'image à partir du chemin
                        FileInputStream input = new FileInputStream(produit.getImagepath());
                        Image image = new Image(input);
                        imageView.setImage(image);
                        imageView.setFitWidth(150);  // Ajuster la largeur de l'image
                        imageView.setFitHeight(150); // Ajuster la hauteur de l'image
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    Region spacer = new Region();
                    HBox.setHgrow(spacer, Priority.ALWAYS);

                    // Créer un bouton de détails
                    Button detailsButton = new Button("Détails");
                    detailsButton.setStyle("-fx-font-size: 12px; -fx-padding: 5px 0px;");
                    detailsButton.setPrefSize(80, 30);
                    detailsButton.setOnAction(event -> afficherDetails(produit));

                    // Créer un bouton de suppression
                    Button deleteButton = new Button("Supprimer");
                    deleteButton.setStyle("-fx-font-size: 12px; -fx-padding: 5px 0px; -fx-background-color: red; -fx-text-fill: white;");
                    deleteButton.setPrefSize(80, 30);
                    deleteButton.setOnAction(event -> deleteProduit(produit));

                    HBox buttonContainer = new HBox(10, detailsButton, deleteButton);
                    buttonContainer.setMaxWidth(Double.MAX_VALUE);
                    buttonContainer.setStyle("-fx-alignment: center-right;");

                    // Créer une VBox pour contenir l'image, le texte et les boutons
                    VBox vbox = new VBox(5, imageView, productText, buttonContainer);
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

    private void afficherDetails(produit produit) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DetailsProduit.fxml"));
            Scene detailsScene = new Scene(loader.load());
            DetailsProduit controller = loader.getController();
            controller.setProduit(produit);
            Stage detailsStage = new Stage();
            detailsStage.setTitle("Détails du produit");
            detailsStage.setScene(detailsScene);
            detailsStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un produit et mettre à jour la ListView
    @FXML
    void deleteProduit(produit produit) {
        if (produit != null) {
            produitService.delete(produit.getIdProduit());
            Platform.runLater(() -> {
                produits.remove(produit); // Suppression du produit directement dans la liste observable
            });
        }
    }

    // Méthode pour ajouter un produit et mettre à jour la ListView
    @FXML
    void addProduit(produit produit) {
        if (produit != null) {
            produitService.add(produit); // Ajout en base de données
            Platform.runLater(() -> {
                produits.add(produit); // Ajout du produit dans la liste observable
            });
        }
    }

    @FXML
    void ReturnToAddProduit(ActionEvent event) {
        try {
            // Charger la scène de la page d'ajout de produit
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterProduit.fxml"));
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
        produits.setAll(produitService.display()); // Recharge les produits
    }
}
