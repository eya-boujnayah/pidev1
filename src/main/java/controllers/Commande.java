package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.CommandeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Commande {
    @FXML
    private TextField searchField; // Champ de recherche
    @FXML
    private Button searchButton;  // Bouton de recherche

    @FXML
    private ListView<String> listViewUser; // Utilisation d'une ListView
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;

    private CommandeService commandeService = new CommandeService();
    private ObservableList<String> commandesList = FXCollections.observableArrayList();

    public Commande() throws SQLException {
    }

    @FXML
    public void initialize() {
        loadData();
    }

    private void loadData() {
        List<model.Commande> commandes = commandeService.display();
        commandesList.clear();
        for (model.Commande commande : commandes) {
            String commandeString = String.format("ID: %d, Utilisateur: %s, Prix: %.2f, Statut: %s, Date: %s",
                    commande.getIdCommande(),
                    commande.getIdUtilisateur(),
                    commande.getPrixCommande(),
                    commande.getStatutCommande(),
                    commande.getDateCommande());
            commandesList.add(commandeString);
        }
        listViewUser.setItems(commandesList);
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
        String selectedCommandeString = listViewUser.getSelectionModel().getSelectedItem();

        if (selectedCommandeString != null) {
            try {
                int idCommande = Integer.parseInt(selectedCommandeString.split(",")[0].split(":")[1].trim());

                model.Commande selectedCommande = commandeService.getCommandeById(idCommande);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/editCommade.fxml"));
                Parent root = loader.load();

                EditCommade modifierController = loader.getController();
                modifierController.setCommande(selectedCommande);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Aucune commande sélectionnée !");
        }
    }


    @FXML
    public void SupprimerCommande(ActionEvent event) {
        String selectedCommandeString = listViewUser.getSelectionModel().getSelectedItem();

        if (selectedCommandeString != null) {
            commandesList.remove(selectedCommandeString);
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

    public void produits(ActionEvent actionEvent) {
        String selectedCommandeString = listViewUser.getSelectionModel().getSelectedItem();

        if (selectedCommandeString != null) {
            try {
                int idCommande = Integer.parseInt(selectedCommandeString.split(",")[0].split(":")[1].trim());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AffichageProduitsCommande.fxml"));
                Parent root = loader.load();

                AffichageProduitsCommande afficherProduitsController = loader.getController();
                afficherProduitsController.setCommandeProduits(idCommande);

                // Afficher la nouvelle fenêtre
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Aucune commande sélectionnée !");
        }
    }

    @FXML
    public void searchCommande(ActionEvent event) {
        String searchText = searchField.getText().toLowerCase();

        ObservableList<String> filteredCommandes = FXCollections.observableArrayList();
        for (String commande : commandesList) {
            if (commande.toLowerCase().contains(searchText)) {
                filteredCommandes.add(commande);
            }
        }

        listViewUser.setItems(filteredCommandes);
    }

}