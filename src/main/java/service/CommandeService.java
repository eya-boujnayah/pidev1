package service;

import model.Commande;
import utils.MyDatabse;
import model.utilisateur;
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
        String sql = "INSERT INTO commande (idUtilisateur, prixTotal, dateCommande, statutCommande) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, commande.getIdUtilisateur().getId());
            pstmt.setFloat(2, commande.getPrixCommande());
            pstmt.setDate(3, new java.sql.Date(commande.getDateCommande().getTime()));
            pstmt.setString(4, commande.getStatutCommande());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Commande commande) {
        String sql = "UPDATE commande SET idUtilisateur = ?, prixTotal = ?, dateCommande = ?, statutCommande = ? WHERE idCommande = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, commande.getIdUtilisateur().getId());
            pstmt.setFloat(2, commande.getPrixCommande());
            pstmt.setDate(3, new java.sql.Date(commande.getDateCommande().getTime()));
            pstmt.setString(4, commande.getStatutCommande());
            pstmt.setInt(5, commande.getIdCommande());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM commande WHERE idCommande = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Commande> display() {
        List<Commande> commandes = new ArrayList<>();
        String sql = "SELECT * FROM commande";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Commande c = new Commande();
                c.setIdCommande(rs.getInt("idCommande"));

                utilisateur user = new utilisateur();
                user.setId(rs.getInt("idUtilisateur"));
                c.setIdUtilisateur(user);

                c.setPrixCommande(rs.getFloat("prixTotal"));
                c.setDateCommande(rs.getDate("dateCommande"));
                c.setStatutCommande(rs.getString("statutCommande"));
                commandes.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandes;
    }


    public List<utilisateur> getAllUtilisateurs() {
        List<utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT * FROM user";  // Assurez-vous que la table utilisateur existe dans la DB

        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                utilisateur user = new utilisateur();
                user.setNom(rs.getString("nom"));
                utilisateurs.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utilisateurs;
    }
}
