package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.produit;
import service.PanierService;
import service.ProduitService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProduitController {
    @FXML
    private GridPane gridPane;
    @FXML
    private Button btnVoirPanier;
    @FXML
    private VBox vboxPanier;

    private ProduitService produitService = new ProduitService();
    private PanierService panierService = new PanierService();

    public ProduitController() throws SQLException {
    }

    @FXML
    public void initialize() {
        afficherProduits();
    }

    private void afficherProduits() {
        List<produit> produits = produitService.display();
        int colonne = 0;
        int ligne = 0;

        for (produit produit : produits) {
            VBox produitBox = new VBox();
            produitBox.setAlignment(Pos.CENTER);
            produitBox.setSpacing(5);
            produitBox.setPrefWidth(140);
            produitBox.setPrefHeight(180);
            produitBox.setStyle("-fx-background-color: #ffffff; -fx-padding: 10; -fx-border-color: #154388; -fx-border-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 1);");

            ImageView imageView = new ImageView(new Image("file:images/default.png"));
            imageView.setFitWidth(70);
            imageView.setFitHeight(70);

            Label nomLabel = new Label(produit.getNom());
            nomLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12px;");

            Label prixLabel = new Label("💰 " + produit.getPrix() + "€");
            prixLabel.setStyle("-fx-text-fill: #154388; -fx-font-size: 10px;");

            Label stockLabel = new Label("📦 " + produit.getStock());
            stockLabel.setStyle("-fx-font-size: 10px;");

            Button btnAjouterPanier = new Button("🛒 Ajouter");
            btnAjouterPanier.setStyle("-fx-background-color: #154388; -fx-text-fill: white; -fx-padding: 5 10; -fx-font-size: 10px; -fx-background-radius: 5;");
            btnAjouterPanier.setOnAction(event -> afficherBoiteDeDialogue(produit));

            produitBox.getChildren().addAll(imageView, nomLabel, prixLabel, stockLabel, btnAjouterPanier);

            gridPane.add(produitBox, colonne, ligne);
            colonne++;

            if (colonne == 2) {
                colonne = 0;
                ligne++;
            }
        }
    }

    private void afficherBoiteDeDialogue(produit produit) {
        // Boîte de dialogue pour saisir la quantité
        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Ajouter au panier");
        dialog.setHeaderText("Ajout de " + produit.getNom());
        dialog.setContentText("Quantité souhaitée :");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(qte -> {
            try {
                int quantite = Integer.parseInt(qte);
                if (quantite > 0 && quantite <= produit.getStock()) {
                    panierService.ajouterAuPanier(produit, quantite); // Appel au service
                } else {
                    afficherAlerte("Quantité invalide !", "Veuillez entrer une quantité entre 1 et " + produit.getStock());
                }
            } catch (NumberFormatException e) {
                afficherAlerte("Erreur", "Veuillez entrer un nombre valide !");
            }
        });
    }

    private void afficherAlerte(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void ouvrirPanier(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Panier.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void backH(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Home.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
