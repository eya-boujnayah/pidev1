package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.produit;
import service.ProduitService;
 import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Node;

import java.io.IOException;
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

    private static produit produitActuel;  // Attribut privé


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

    @FXML
    void updtprd(ActionEvent event) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateProduit.fxml"));
            Scene detailsScene = new Scene(loader.load());
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(detailsScene);
            currentStage.show();
            //recuperation de
            UpdateProduit controller = loader.getController();

            controller.setUpdateProduit(produitActuel);
        } catch (Exception e) {
            System.out.println("Erreur inattendue : " + e.getMessage());
            e.printStackTrace();
        }
    }






}


