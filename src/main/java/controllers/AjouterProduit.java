package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

    }


