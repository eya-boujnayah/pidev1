package service;

import model.Commande;
import utils.MyDatabse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandeService implements IService<Commande> {

    Connection con;

    public CommandeService() {
        con = MyDatabse.getInstance().getCon();
    }
    @Override
    public void add(Commande commande) {
        String sql = "INSERT INTO `commande`(`idUtilisateur`, `prixTotal`, `dateCommande`, `statutCommande`) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, commande.getIdUtilisateur());
            pstmt.setFloat(2, commande.getPrixCommande());
            pstmt.setDate(3, new java.sql.Date(commande.getDateCommande().getTime()));
            pstmt.setString(4, commande.getStatutCommande());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding commande: " + e.getMessage());
        }
    }

    @Override
    public void update(Commande commande) {
        String sql = "UPDATE `commande` SET `idUtilisateur` = ?, `prixTotal` = ?, `dateCommande` = ?, `statutCommande` = ? WHERE `idCommande` = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, commande.getIdUtilisateur());
            pstmt.setFloat(2, commande.getPrixCommande());
            pstmt.setDate(3, new java.sql.Date(commande.getDateCommande().getTime()));
            pstmt.setString(4, commande.getStatutCommande());
            pstmt.setInt(5, commande.getIdCommande());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating commande: " + e.getMessage());
        }

    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM `commande` WHERE `idCommande` = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting commande: " + e.getMessage());
        }
    }

    @Override
    public List<Commande> display() {
        List<Commande> commandes = new ArrayList<>();
        String sql = "SELECT * FROM `commande`";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Commande c = new Commande();
                c.setIdCommande(rs.getInt("idCommande"));
                c.setIdUtilisateur(rs.getInt("idUtilisateur"));
                c.setPrixCommande(rs.getFloat("prixTotal"));
                c.setDateCommande(rs.getDate("dateCommande"));
                c.setStatutCommande(rs.getString("statutCommande"));
                commandes.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching commandes: " + e.getMessage());
        }

        return commandes;
    }
}
