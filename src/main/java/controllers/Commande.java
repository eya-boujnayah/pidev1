package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.CommandeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public class Commande {
    @FXML
    private TableView<model.Commande> tableviewUser;
    @FXML
    private TableColumn<model.Commande, Integer> idCommande;
    @FXML
    private TableColumn<model.Commande, Integer> idUtilisateur;
    @FXML
    private TableColumn<model.Commande, Float> prixCommande;
    @FXML
    private TableColumn<model.Commande, String> statutCommande;
    @FXML
    private TableColumn<model.Commande, String> dateCommande;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;

    private CommandeService commandeService = new CommandeService();
    private ObservableList<model.Commande> commandesList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Associer les colonnes aux propriétés de Commande
        idCommande.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
        idUtilisateur.setCellValueFactory(new PropertyValueFactory<>("idUtilisateur")); // Vérifie que idUtilisateur a une méthode getIdUtilisateur() qui retourne un int
        prixCommande.setCellValueFactory(new PropertyValueFactory<>("prixCommande"));
        statutCommande.setCellValueFactory(new PropertyValueFactory<>("statutCommande"));
        dateCommande.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));

        // Charger les données
        loadData();
    }


    private void loadData() {
        List<model.Commande> commandes = commandeService.display();
        commandesList.setAll(commandes);
        tableviewUser.setItems(commandesList);
    }

    @FXML
    public void AjouterCommande(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AjouterCommande.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void ModifierCommande(ActionEvent event) {
        model.Commande selectedCommande = tableviewUser.getSelectionModel().getSelectedItem();

        if (selectedCommande != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/editCommade.fxml"));
                Parent root = loader.load();

                // Récupérer le contrôleur et lui passer la commande sélectionnée
                EditCommade modifierController = loader.getController();
                modifierController.setCommande(selectedCommande);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Aucune commande sélectionnée !");
        }
    }



    @FXML
    public void SupprimerCommande(ActionEvent event) {
        model.Commande selectedCommande = tableviewUser.getSelectionModel().getSelectedItem();

        if (selectedCommande != null) {
            commandeService.delete(selectedCommande.getIdCommande());
            commandesList.remove(selectedCommande);
            System.out.println("Commande supprimée !");
        } else {
            System.out.println("Aucune commande sélectionnée !");
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
