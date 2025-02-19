package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import model.produit;
import service.ProduitService;

public class TousLesProduits {

    @FXML
    private ListView<produit> listViewProduits;

    private final ProduitService produitService = new ProduitService();

    @FXML
    public void initialize() {
        // Loading products asynchronously if needed
        ObservableList<produit> produits = FXCollections.observableArrayList(produitService.display());
        listViewProduits.setItems(produits);

        // Set the custom cell factory using lambda
        listViewProduits.setCellFactory(param -> new ListCell<produit>() {
            @Override
            protected void updateItem(produit produit, boolean empty) {
                super.updateItem(produit, empty);
                if (empty || produit == null) {
                    setText(null);
                } else {
                    setText(produit.getNom() + " - " + produit.getDescription());
                }
            }
        });
    }
}
