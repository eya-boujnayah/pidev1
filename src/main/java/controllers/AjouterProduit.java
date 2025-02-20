package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;
 import javafx.scene.Node;

import model.produit;
import service.IService;
import service.ProduitService;

import java.io.IOException;
import java.sql.SQLException;

    public class AjouterProduit {


        @FXML
        private TextField categorie;

        @FXML
        private TextField couleurs;

        @FXML
        private TextArea description;

        @FXML
        private TextField imagepath;

        @FXML
        private TextField marque;

        @FXML
        private TextField nom;

        @FXML
        private TextField prix;

        @FXML
        private TextField reference;

        @FXML
        private TextField status;

        @FXML
        private TextField stock;
private IService ps = new ProduitService();
        @FXML
        void addProduit(ActionEvent event) throws SQLException {
            double prixValue = Double.parseDouble(prix.getText());
            int stockValue = Integer.parseInt(stock.getText());
            int idCategorie = Integer.parseInt(categorie.getText());
            ps.add(new produit(reference.getText(),
                    nom.getText(),
                    description.getText(),

                    prixValue,
                    marque.getText(),
                    stockValue,
                    couleurs.getText(),
                    status.getText(),
                    imagepath.getText(),
                    idCategorie));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("success");
            alert.setContentText("produit ajouté avec succés ");
            alert.showAndWait();
            reference.clear();
            nom.clear();
            description.clear();
            prix.clear();
            marque.clear();
            stock.clear();
            couleurs.clear();
            status.clear();
            imagepath.clear();
            categorie.clear();
        }
        @FXML
        void listproduit(ActionEvent event) {
            try {
                // Charger la scène de la liste des produits
                Parent root = FXMLLoader.load(getClass().getResource("/TousLesProduits.fxml"));

                // Créer une nouvelle scène
                Scene scene = new Scene(root);

                // Récupérer la fenêtre actuelle (Stage) et changer la scène
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de Chargement");
                alert.setContentText("Il y a eu une erreur lors du chargement de la liste des produits.");
                alert.showAndWait();
            }
        }
        @FXML
        void ListCagProd(ActionEvent event) {
            try {
                // Charger le fichier FXML pour l'interface AjouterCategorie
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterCategorie.fxml"));
                Parent root = loader.load();

                // Créer une nouvelle scène avec l'interface AjouterCategorie
                Scene scene = new Scene(root);

                // Récupérer la fenêtre actuelle (Stage) et changer la scène
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);

                // Optionnel : vous pouvez récupérer le contrôleur de l'interface AjouterCategorie si vous avez besoin de faire des interactions
                AjouterCategorie ajouterCategorieController = loader.getController();
                // Vous pouvez maintenant appeler des méthodes du contrôleur de AjouterCategorie si nécessaire

            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de Chargement");
                alert.setContentText("Il y a eu une erreur lors du chargement de l'interface AjouterCategorie.");
                alert.showAndWait();
            }
        }


    }


