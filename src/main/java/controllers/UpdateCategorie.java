package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.categorie;
import service.CategorieService;

import javax.swing.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class UpdateCategorie {

    @FXML
    private ComboBox<categorie> categoriefComboBox;

    @FXML
    private Button AnnulerUpdate;

    @FXML
    private TextField codef;

    @FXML
    private DatePicker dateuc;

    @FXML
    private TextArea descriptionf;

    @FXML
    private TextField nomf;

    @FXML
    private Button updateCategorie;

    private categorie currentCategorie;  // The category to be updated

    public void setUpdateCategorie(categorie categorie) {
        this.currentCategorie = categorie;

        // Fill in the fields with the current category data
        if (categorie != null) {
            nomf.setText(categorie.getNom());
            descriptionf.setText(categorie.getDescription());
            codef.setText(categorie.getReference());

            // Convert java.util.Date to java.sql.Date if necessary and then to LocalDate
            if (categorie.getDateCreation() != null) {
                // Convert java.util.Date to java.sql.Date
                java.util.Date utilDate = categorie.getDateCreation();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // Convert to sql.Date

                // Convert java.sql.Date to LocalDate
                LocalDate dateCreation = sqlDate.toLocalDate(); // Convert to LocalDate
                dateuc.setValue(dateCreation); // Set the LocalDate to the DatePicker
            }
        }
    }




    @FXML
    void AnnulerUpdate(ActionEvent event) {
        // Close the window when cancel is clicked
        ((Button) event.getSource()).getScene().getWindow().hide();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void updateCategorie(ActionEvent event) {
        try {
            // Récupérer les valeurs saisies
            currentCategorie.setNom(nomf.getText());
            currentCategorie.setDescription(descriptionf.getText());
            currentCategorie.setReference(codef.getText());

            // Convertir LocalDate en java.util.Date
            LocalDate localDate = dateuc.getValue();
            if (localDate != null) {
                Date dateCreation = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                currentCategorie.setDateCreation(dateCreation);
            }

            // Créer une instance de CategorieService pour valider
            CategorieService categorieService = new CategorieService();

            // Appeler la méthode de validation avant de mettre à jour
            if (!categorieService.isValidCategorie(currentCategorie)) {
                // Si la validation échoue, afficher un message d'erreur
                showAlert(Alert.AlertType.ERROR, "Erreur de validation",
                        "Les données saisies ne sont pas valides !\n" +
                                "- La référence doit avoir exactement 4 caractères.\n" +
                                "- Le nom ne peut pas être vide.\n" +
                                "- La description ne peut pas être vide.\n" +
                                "- La date de création doit être antérieure à la date actuelle.");
                return;  // Arrêter l'exécution de la méthode
            }

            // Si la validation est réussie, procéder à la mise à jour
            categorieService.update(currentCategorie); // Appeler la méthode update sur l'instance

            // Afficher un message de succès
            showAlert(Alert.AlertType.INFORMATION, "Mise à jour réussie", "Les informations de la catégorie ont été mises à jour.");

            // Fermer la fenêtre après la mise à jour
            ((Button) event.getSource()).getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
            // Afficher un message d'erreur en cas d'échec
            showAlert(Alert.AlertType.ERROR, "Erreur", "Une erreur est survenue lors de la mise à jour.");
        }
    }






}
