package tn.esprit.services;

import tn.esprit.Interfaces.Iservices;
import tn.esprit.models.User;
import tn.esprit.util.Maconnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Userservice implements Iservices<User> {

    Connection con;

    public Userservice() {
        con = Maconnexion.getInstance().getCon();
    }

    @Override
    public void add(User user) {
        String sql = "INSERT INTO `user` ( `nom`, `prenom`, `email`, `mdp`, `telephone`, `role`, `date_inscription`, `date_naissance`) " +
                "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, user.getNom());
            preparedStatement.setString(2, user.getPrenom());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getMdp());
            preparedStatement.setString(5, user.getTelephone());
            preparedStatement.setString(6, user.getRole());
            preparedStatement.setDate(7, new java.sql.Date(user.getDateInscription().getTime()));
            preparedStatement.setDate(8, new java.sql.Date(user.getDateNaissance().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE user SET nom = ?, prenom = ?, email = ?, mdp = ?, telephone = ?, role = ?, date_inscription = ?, date_naissance = ? WHERE idUtilisateur = ?";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, user.getNom());
            preparedStatement.setString(2, user.getPrenom());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getMdp());
            preparedStatement.setString(5, user.getTelephone());
            preparedStatement.setString(6, user.getRole());
            preparedStatement.setDate(7, new java.sql.Date(user.getDateInscription().getTime()));
            preparedStatement.setDate(8, new java.sql.Date(user.getDateNaissance().getTime()));
            preparedStatement.setInt(9, user.getIdUtilisateur());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Utilisateur mis à jour avec succès !");
            } else {
                System.out.println("Aucune mise à jour effectuée. Vérifiez l'ID utilisateur.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour : " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM `user` WHERE `idUtilisateur` = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting user: " + e.getMessage());
        }
    }

    @Override
    public List<User> display() {
        String query = "SELECT * FROM user";
        List<User> users = new ArrayList<>();

        try {
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setIdUtilisateur(rs.getInt("idUtilisateur"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setEmail(rs.getString("email"));
                user.setMdp(rs.getString("mdp"));
                user.setTelephone(rs.getString("telephone"));
                user.setRole(rs.getString("role"));
                user.setDateInscription(rs.getDate("date_inscription"));
                user.setDateNaissance(rs.getDate("date_naissance"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public List<User> getAll() {
        return display();
    }

    @Override
    public User get(int id) {
        String query = "SELECT * FROM user WHERE idUtilisateur = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("idUtilisateur"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mdp"),
                        rs.getString("telephone"),
                        rs.getString("role"),
                        rs.getDate("date_inscription"),
                        rs.getDate("date_naissance")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
