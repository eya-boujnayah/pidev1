package service;

import model.Facture;
import model.Commande;
import model.Utilisateur;
import utils.MyDatabse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
public class FactureService implements IService<Facture> {

    private final Connection con;

    public FactureService() throws SQLException {
        con = MyDatabse.getInstance().getCon();

    }

    @Override
    public void add(Facture facture) {
        String sql = "INSERT INTO `facture`(`idCommande`, `idUtilisateur`, `prixTotal`, `TypePaiement`, `AdresseLivraison`, `dateFacture`, `statutFacture`) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, facture.getCommande().getIdCommande());
            pstmt.setInt(2, facture.getUtilisateur().getIdUtilisateur());
            pstmt.setFloat(3, facture.getPrixTotal());
            pstmt.setString(4, facture.getTypePaiement());
            pstmt.setString(5, facture.getAdresseLivraison());
            pstmt.setDate(6, new java.sql.Date(facture.getDateFacture().getTime()));
            pstmt.setString(7, facture.getStatutFacture());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de la facture : " + e.getMessage());
        }
    }

    @Override
    public void update(Facture facture) {
        String sql = "UPDATE `facture` SET `idCommande` = ?, `idUtilisateur` = ?, `prixTotal` = ?, `TypePaiement` = ?, `AdresseLivraison` = ?, `dateFacture` = ?, `statutFacture` = ? WHERE `idFacture` = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, facture.getCommande().getIdCommande()); // Utilisation de l'objet Commande
            pstmt.setInt(2, facture.getUtilisateur().getIdUtilisateur()); // Correct, envoi l'ID de l'utilisateur
            pstmt.setFloat(3, facture.getPrixTotal());
            pstmt.setString(4, facture.getTypePaiement());
            pstmt.setString(5, facture.getAdresseLivraison());
            pstmt.setDate(6, new java.sql.Date(facture.getDateFacture().getTime()));
            pstmt.setString(7, facture.getStatutFacture());
            pstmt.setInt(8, facture.getIdFacture());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating facture: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM `facture` WHERE `idFacture` = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression : " + e.getMessage());


            
        }
    }

    @Override
    public List<Facture> display() {
        List<Facture> factures = new ArrayList<>();
        String sql = "SELECT f.*, u.email, u.nom FROM facture f JOIN user u ON f.idUtilisateur = u.idUtilisateur";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Facture facture = new Facture();
                Commande commande = new Commande();
                Utilisateur user = new Utilisateur();

                commande.setIdCommande(rs.getInt("idCommande"));
                user.setIdUtilisateur(rs.getInt("idUtilisateur"));
                user.setEmail(rs.getString("email"));  // Charger l'email de l'utilisateur
                user.setNom(rs.getString("nom"));  // Charger le nom si nécessaire

                facture.setIdFacture(rs.getInt("idFacture"));
                facture.setCommande(commande);
                facture.setUtilisateur(user); // Associer l'utilisateur à la facture
                facture.setPrixTotal(rs.getFloat("prixTotal"));
                facture.setTypePaiement(rs.getString("TypePaiement"));
                facture.setAdresseLivraison(rs.getString("AdresseLivraison"));
                facture.setDateFacture(rs.getDate("dateFacture"));
                facture.setStatutFacture(rs.getString("statutFacture"));

                factures.add(facture);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des factures : " + e.getMessage());
        }

        return factures;
    }

    public List<Integer> getAllCommandes() {
        List<Integer> commandes = new ArrayList<>();
        String query = "SELECT idCommande FROM commande";

        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                commandes.add(rs.getInt("idCommande"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandes;
    }

    public List<Integer> getAllUtilisateurs() {
        List<Integer> utilisateurs = new ArrayList<>();
        String query = "SELECT * FROM user";

        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                utilisateurs.add(rs.getInt("idUtilisateur"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }

    public Facture getFactureById(int id) {
        for (model.Facture facture : display()) {
            if (facture.getIdFacture() == id) {
                return facture;
            }
        }
        return null;
    }





}