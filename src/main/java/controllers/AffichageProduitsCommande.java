package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.CommandeProduit;
import service.CommandeService;
import model.produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class AffichageProduitsCommande {
    @FXML
    private ListView<String> listViewProduits;

    private CommandeService commandeService = new CommandeService();
    private ObservableList<String> produitsList = FXCollections.observableArrayList();

    public AffichageProduitsCommande() throws SQLException {
    }

    // Cette méthode va être appelée pour afficher les produits d'une commande
    public void setCommandeProduits(int idCommande) {
        List<CommandeProduit> produits = commandeService.getProduitsByCommandeId(idCommande);

        if (produits.isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Aucun produit");
            alert.setHeaderText("Aucun produit trouvé pour cette commande.");
            alert.showAndWait();
        } else {
            for (CommandeProduit produit : produits) {
                String produitString = String.format("ID: %d, Nom: %s, Prix: %.2f", produit.getProduit().getIdProduit(), produit.getProduit().getNom(), produit.getPrixUnitaire());
                produitsList.add(produitString);
            }
            listViewProduits.setItems(produitsList);
        }
    }

    // Méthode pour fermer la fenêtre des produits
    public void closeWindow() {
        // Fermer la fenêtre actuelle (juste un exemple ici)
        System.out.println("Fenêtre fermée");
    }
}
