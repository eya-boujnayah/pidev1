    package controllers;

    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Scene;
    import javafx.scene.control.*;
    import javafx.scene.layout.AnchorPane;
    import javafx.stage.Stage;
    import model.Commande;
    import model.Utilisateur;
    import service.CommandeService;

    import java.sql.Date;
    import java.sql.SQLException;
    import java.time.LocalDate;
    import java.time.ZoneId;
    import java.util.List;

    public class EditCommade {

        @FXML
        private TextField prixCommande;

        @FXML
        private TextField statutCommande;

        @FXML
        private DatePicker dateCommande;

        @FXML
        private ChoiceBox<Utilisateur> idUtilisateur;
        @FXML
        private Button back;
        private CommandeService cs = new CommandeService();
        private Commande commande;

        public EditCommade() throws SQLException {
        }

        public void initialize() {
            loadUtilisateurs();
        }

        private void loadUtilisateurs() {
            List<Utilisateur> utilisateurs = cs.getAllUtilisateurs();
            idUtilisateur.getItems().setAll(utilisateurs);
        }

        public void setCommande(Commande commande) {
            this.commande = commande;
            if (commande != null) {
                prixCommande.setText(String.valueOf(commande.getPrixCommande()));
                statutCommande.setText(commande.getStatutCommande());
                dateCommande.setValue(new java.sql.Date(commande.getDateCommande().getTime()).toLocalDate());
                idUtilisateur.setValue(commande.getIdUtilisateur());
            }
        }

        @FXML
        public void EditCommande(ActionEvent actionEvent) {
            if (commande == null) {
                showAlert("Erreur", "Aucune commande sélectionnée !");
                return;
            }

            try {
                Utilisateur user = idUtilisateur.getValue();
                float prix = Float.parseFloat(prixCommande.getText());
                String statut = statutCommande.getText();
                LocalDate localDate = dateCommande.getValue();
                Date date = Date.valueOf(localDate);

                commande.setPrixCommande(prix);
                commande.setStatutCommande(statut);
                commande.setDateCommande(date);
                commande.setIdUtilisateur(user);

                cs.update(commande);

                showAlert("Succès", "Commande mise à jour avec succès !");
            } catch (NumberFormatException e) {
                showAlert("Erreur", "Le prix doit être un nombre valide !");
            }
        }

        @FXML
        public void AnnulerCommande(ActionEvent actionEvent) {
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

        public void back(ActionEvent actionEvent) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Commande.fxml"));
                AnchorPane root = loader.load();

                Scene scene = new Scene(root);

                Stage currentStage = (Stage) back.getScene().getWindow();

                currentStage.setScene(scene);
                currentStage.show();
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Erreur", "Impossible de charger l'interface des commandes !");
            }
        }
    }
