package service;

import model.categorie;
import utils.MyDatabse;

import java.sql.*;
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

}
