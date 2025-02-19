package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.produit;
import service.ProduitService;

public class DetailsProduit {

    @FXML private Label labelReference;
    @FXML private Label labelNomProduit;
    @FXML private Label labelDescriptionProduit;
    @FXML private Label labelMarque;
    @FXML private Label labelPrixProduit;
    @FXML private Label labelStock;
    @FXML private Label labelCouleurs;
    @FXML private Label labelStatus;
    @FXML private Label labelIdCategorie;

    private produit produitActuel;
    private ProduitService produitService = new ProduitService(); // Création d'une instance de ProduitService

    // Méthode pour définir les détails du produit
    public void setProduit(produit produit) {
        this.produitActuel = produit;
        labelReference.setText("Référence : " + produit.getReference());
        labelNomProduit.setText("Nom : " + produit.getNom());
        labelDescriptionProduit.setText("Description : " + produit.getDescription());
        labelMarque.setText("Marque : " + produit.getMarque());
        labelPrixProduit.setText("Prix : " + produit.getPrix() + "Dt");
        labelStock.setText("Stock : " + produit.getStock());
        labelCouleurs.setText("Couleurs : " + produit.getCouleurs());
        labelStatus.setText("Statut : " + produit.getStatus());
        labelIdCategorie.setText(" Catégorie : " + produit.getIdCategorie());
    }

    // Fermer la fenêtre actuelle
    @FXML
    private void fermerFenetre() {
        Stage stage = (Stage) labelNomProduit.getScene().getWindow();
        stage.close();
    }

}
