package service;

import javafx.scene.control.Alert;
import model.categorie;
import utils.MyDatabse;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CategorieService implements IService<categorie> {
    Connection con;

    public CategorieService() {
        con = MyDatabse.getInstance().getCon();
    }

    @Override
    public void add(categorie categorie) {
        // SQL query to insert data, including reference, nom, description, and date_creation
        String sql = "INSERT INTO `categorie`(`reference`, `nom`, `description`, `date_creation`) " +
                "VALUES ('" + categorie.getReference() + "', '" +
                categorie.getNom() + "', '" +
                categorie.getDescription() + "', '" +
                new java.sql.Date(categorie.getDateCreation().getTime()) + "')"; // Ensure date is converted to java.sql.Date

        try {
            // Create the statement
            Statement statement = con.createStatement();
            // Execute the SQL query
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            // Handle SQL exceptions
            System.err.println("Erreur d'ajout de la catégorie : " + e.getMessage());
        }
    }

    @Override
    public void update(categorie categorie) {
        // SQL query to update the category details
        String sql = "UPDATE `categorie` SET `reference` = ?, `nom` = ?, `description` = ?, `date_creation` = ? WHERE `idCategorie` = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            // Set the parameters in the prepared statement
            preparedStatement.setString(1, categorie.getReference());
            preparedStatement.setString(2, categorie.getNom());
            preparedStatement.setString(3, categorie.getDescription());
            preparedStatement.setDate(4, new java.sql.Date(categorie.getDateCreation().getTime()));
            preparedStatement.setInt(5, categorie.getIdCategorie());

            // Execute the update
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour de la catégorie : " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        // SQL query to delete a category based on its ID
        String sql = "DELETE FROM `categorie` WHERE `idCategorie` = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);  // Set the category ID for deletion
            pstmt.executeUpdate(); // Execute the delete query
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de la catégorie : " + e.getMessage());
        }
    }

    public List<categorie> display() {
        String query = "SELECT * FROM `categorie`";
        List<categorie> categories = new ArrayList<>();

        try (Statement statement = con.createStatement(); ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                categorie c = new categorie();
                c.setIdCategorie(rs.getInt("idCategorie"));
                c.setReference(rs.getString("reference"));
                c.setNom(rs.getString("nom"));
                c.setDescription(rs.getString("description"));
                c.setDateCreation(rs.getDate("date_creation")); // Populate date creation from result set
                categories.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'affichage des catégories : " + e.getMessage());
        }

        return categories;
    }
    public categorie getCategorieById(int id) {
        for (categorie cat : display()) { // display() doit renvoyer la liste des catégories
            if (cat.getIdCategorie() == id) {
                return cat;
            }
        }
        return null; // Retourne null si aucune catégorie ne correspond à l'ID
    }

    public categorie getCategorieByNom(String nom) {
        categorie category = null;
        String query = "SELECT * FROM categorie WHERE nom = ?";

        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, nom);  // Set the category name in the query
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Create a new categorie object and set the properties
                category = new categorie();
                category.setIdCategorie(rs.getInt("idCategorie"));
                category.setReference(rs.getString("reference"));
                category.setNom(rs.getString("nom"));
                category.setDescription(rs.getString("description"));
                category.setDateCreation(rs.getDate("date_creation"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;  // Return the category object or null if not found
    }
    // Fonction pour valider une catégorie avant de l'ajouter
    public boolean isValidCategorie(categorie cat) {
        return isReferenceValid(cat.getReference()) &&
                isNomValid(cat.getNom()) &&
                isDescriptionValid(cat.getDescription()) &&
                isDateValid(new java.sql.Date(cat.getDateCreation().getTime())); // Convertir en java.sql.Date
    }


    // Vérification que la référence a exactement 4 caractères
    public boolean isReferenceValid(String reference) {
        return reference != null && reference.length() == 4;
    }

    // Vérification que le nom n'est pas vide
    public boolean isNomValid(String nom) {
        return nom != null && !nom.trim().isEmpty();
    }

    // Vérification que la description n'est pas vide
    public boolean isDescriptionValid(String description) {
        return description != null && !description.trim().isEmpty();
    }

    // Vérification que la date est antérieure à aujourd'hui
    public boolean isDateValid(Date date) {
        return date != null && date.toLocalDate().isBefore(LocalDate.now());
    }

    // Ajout d'une catégorie avec contrôle de validation
    public boolean addCategorie(categorie cat) {
        if (!isValidCategorie(cat)) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Les données de la catégorie ne sont pas valides !");
            return false;
        }

        try {
            // Ici, tu devrais insérer la catégorie dans la base de données
            System.out.println("Catégorie ajoutée avec succès : " + cat.getNom());
            showAlert(Alert.AlertType.CONFIRMATION, "Succès", "Catégorie ajoutée avec succès !");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Une erreur est survenue lors de l'ajout.");
            return false;
        }
    }

    // Méthode utilitaire pour afficher une alerte
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }


}
