package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.Facture;
import service.FactureService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EditFacture {

    @FXML
    private TextField prixTotal;
    @FXML
    private TextField typePaiement;
    @FXML
    private TextField adresseLivraison;
    @FXML
    private TextField statutFacture;
    @FXML
    private DatePicker dateFacture;
    @FXML
    private ChoiceBox<Integer> idUtilisateur;
    @FXML
    private ChoiceBox<Integer> idCommande;

    private Facture selectedFacture;
    private final FactureService factureService = new FactureService();

    public EditFacture() throws SQLException {
    }

    public void initData(Facture facture) {
        this.selectedFacture = facture;

        idUtilisateur.setValue(facture.getUtilisateur().getIdUtilisateur());
        idCommande.setValue(facture.getCommande().getIdCommande());
        prixTotal.setText(String.valueOf(facture.getPrixTotal()));
        typePaiement.setText(facture.getTypePaiement());
        adresseLivraison.setText(facture.getAdresseLivraison());
        statutFacture.setText(facture.getStatutFacture());
        java.util.Date utilDate = facture.getDateFacture();
        if (utilDate != null) {
            dateFacture.setValue(new java.sql.Date(utilDate.getTime()).toLocalDate());
        }
        loadUtilisateurs();
        loadCommandes();
    }

    @FXML
    public void EditFacture(ActionEvent actionEvent) {
        if (selectedFacture != null) {
            selectedFacture.setPrixTotal(Float.parseFloat(prixTotal.getText()));
            selectedFacture.setTypePaiement(typePaiement.getText());
            selectedFacture.setAdresseLivraison(adresseLivraison.getText());
            selectedFacture.setStatutFacture(statutFacture.getText());
            selectedFacture.setDateFacture(java.sql.Date.valueOf(dateFacture.getValue()));

            // Mise à jour dans la base de données
            factureService.update(selectedFacture);

            // Retour à la liste des factures
            back(actionEvent);
        }
    }

    @FXML
    public void AnnulerFacture(ActionEvent actionEvent) {
        back(actionEvent);
    }

    @FXML
    public void back(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Factures.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void loadUtilisateurs() {
        List<Integer> utilisateurs = factureService.getAllUtilisateurs();
        idUtilisateur.getItems().addAll(utilisateurs);
    }

    private void loadCommandes() {
        List<Integer> commandes = factureService.getAllCommandes();
        idCommande.getItems().addAll(commandes);
    }
}