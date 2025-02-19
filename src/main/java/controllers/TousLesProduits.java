package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.produit;
import service.ProduitService;

public class TousLesProduits {

    @FXML
    private ListView<produit> listViewProduits;

    private final ProduitService produitService = new ProduitService();

    @FXML
    public void initialize() {
        // Charger les produits
        ObservableList<produit> produits = FXCollections.observableArrayList(produitService.display());
        listViewProduits.setItems(produits);

        // Personnaliser l'affichage de chaque élément de la liste
        listViewProduits.setCellFactory(param -> new ListCell<produit>() {
            @Override
            protected void updateItem(produit produit, boolean empty) {
                super.updateItem(produit, empty);
                if (empty || produit == null) {
                    setGraphic(null);
                } else {
                    // Texte du produit
                    Text productText = new Text(produit.getNom() + " :  " + produit.getDescription() +"  "+ produit.getImagepath());

                    // Espace pour séparer le texte du bouton
                    Region spacer = new Region();
                    HBox.setHgrow(spacer, Priority.ALWAYS);

                    // Bouton "Détails"
                    Button detailsButton = new Button("Détails");
                    detailsButton.setStyle("-fx-font-size: 12px; -fx-padding: 5px 0px;");
                    detailsButton.setPrefSize(80, 30);
                    detailsButton.setOnAction(event -> afficherDetails(produit));

                    // Conteneur du bouton (HBox pour aligner à droite)
                    HBox buttonContainer = new HBox(detailsButton);
                    buttonContainer.setMaxWidth(Double.MAX_VALUE);
                    buttonContainer.setStyle("-fx-alignment: center-right;");

                    // Conteneur principal (VBox pour empiler les éléments)
                    VBox vbox = new VBox(5, productText, buttonContainer);
                    vbox.setStyle("-fx-padding: 10px;");

                    setGraphic(vbox);
                }
            }
        });
    }

    private void afficherDetails(produit produit) {
        System.out.println("Détails du produit : " + produit.getNom());
        // Ici, tu peux afficher une nouvelle fenêtre ou charger une autre vue avec les détails.
    }
}
