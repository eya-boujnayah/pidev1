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



    @FXML
    void updateCategorie(ActionEvent event) {
        try {
            // Mettre à jour les informations de la catégorie
            currentCategorie.setNom(nomf.getText());
            currentCategorie.setDescription(descriptionf.getText());
            currentCategorie.setReference(codef.getText());

            // Convertir LocalDate en java.util.Date
            LocalDate localDate = dateuc.getValue();
            if (localDate != null) {
                Date dateCreation = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                currentCategorie.setDateCreation(dateCreation);
            }

            // Créer une instance de CategorieService pour sauvegarder
            CategorieService categorieService = new CategorieService();
            categorieService.update(currentCategorie); // Appeler la méthode update sur l'instance

            // Afficher un message de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mise à jour réussie");
            alert.setHeaderText("Catégorie mise à jour avec succès");
            alert.setContentText("Les informations de la catégorie ont été mises à jour.");
            alert.showAndWait();

            // Fermer la fenêtre après la mise à jour
            ((Button) event.getSource()).getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
            // Afficher un message d'erreur en cas d'échec
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Échec de la mise à jour");
            alert.setContentText("Vérifiez les données saisies.");
            alert.showAndWait();
        }
    }




}
