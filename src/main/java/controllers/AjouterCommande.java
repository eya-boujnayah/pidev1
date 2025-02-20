package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Commande;
import model.utilisateur;
import service.CommandeService;
import service.PersonneService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class AjouterCommande {

    @FXML
    private DatePicker dateCommande;

    @FXML
    private TextField prixCommande;

    @FXML
    private TextField statutCommande;

    @FXML
    private ChoiceBox<utilisateur> idUtilisateur;

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnAnnuler;
    @FXML
    private Button back;

    private CommandeService cs = new CommandeService();

    @FXML
    public void initialize() {
        loadUtilisateurs(); // Charger les utilisateurs dans le ChoiceBox
    }

    private void loadUtilisateurs() {
        List<utilisateur> utilisateurs = cs.getAllUtilisateurs();
        idUtilisateur.getItems().setAll(utilisateurs);
    }

    @FXML
    public void AjouterCommande(ActionEvent event) {
        if (idUtilisateur.getValue() == null || prixCommande.getText().isEmpty() || statutCommande.getText().isEmpty() || dateCommande.getValue() == null) {
            showAlert("Erreur", "Veuillez remplir tous les champs !");
            return;
        }

        try {
            utilisateur user = idUtilisateur.getValue();
            float prix = Float.parseFloat(prixCommande.getText());
            String statut = statutCommande.getText();
            LocalDate localDate = dateCommande.getValue();
            Date date = Date.valueOf(localDate);

            Commande nouvelleCommande = new Commande(0, user, prix, statut, date);
            cs.add(nouvelleCommande);

            showAlert("Succès", "Commande ajoutée avec succès !");
            AnnulerCommandee();
        } catch (NumberFormatException e) {
            showAlert("Erreur", "Le prix doit être un nombre valide !");
        }
    }

    @FXML
    public void AnnulerCommande(ActionEvent event) {
        prixCommande.clear();
        statutCommande.clear();
        dateCommande.setValue(null);
        idUtilisateur.setValue(null);
    }

    private void showAlert(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    public void back(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML Commande.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Commande.fxml"));
            AnchorPane root = loader.load();

            // Créer une nouvelle scène avec le FXML chargé
            Scene scene = new Scene(root);

            // Récupérer la scène actuelle
            Stage currentStage = (Stage) back.getScene().getWindow();

            // Définir la nouvelle scène dans la fenêtre principale
            currentStage.setScene(scene);
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible de charger l'interface des commandes !");
        }

    }

    public void AnnulerCommandee() {
        prixCommande.clear();
        statutCommande.clear();
        dateCommande.setValue(null);
        idUtilisateur.setValue(null);
    }
}
