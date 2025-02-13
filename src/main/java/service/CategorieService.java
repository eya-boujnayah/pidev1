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
        // Requête SQL pour insérer les données
        String sql = "INSERT INTO `categorie`(`nom`, `description`, `status`, `date_creation`) VALUES ('" +
                categorie.getNom() + "','" +
                categorie.getDescription() + "','" +
                categorie.getStatus() + "','" +
                new java.sql.Date(categorie.getDate_creation().getTime()) + "')";
        try {
            // Création de la déclaration
            Statement statement = con.createStatement();
            // Exécution de la requête SQL
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            // Gestion des exceptions SQL
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(categorie categorie) {
        String sql = "UPDATE `categorie` SET nom= ?, description= ?, status= ?, date_creation= ? WHERE idCategorie= ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, categorie.getNom());
            preparedStatement.setString(2, categorie.getDescription());
            preparedStatement.setString(3, categorie.getStatus());
            preparedStatement.setDate(4, new java.sql.Date(categorie.getDate_creation().getTime()));
            preparedStatement.setInt(5, categorie.getIdCategorie());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM categorie WHERE idCategorie = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting categorie: " + e.getMessage());
        }
    }

    public List<categorie> display() {
        String query = "SELECT * FROM `categorie`";
        List<categorie> categories = new ArrayList<>();

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                categorie c = new categorie();

                c.setIdCategorie(rs.getInt("idCategorie"));
                c.setNom(rs.getString("nom"));
                c.setDescription(rs.getString("description"));
                c.setStatus(rs.getString("status"));
                c.setDate_creation(rs.getDate("date_creation"));

                categories.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des catégories : " + e.getMessage());
        }

        return categories;
    }
}
