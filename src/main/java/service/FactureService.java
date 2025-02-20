package service;

import model.Facture;
import model.Commande;
import model.utilisateur;
import utils.MyDatabse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FactureService implements IService<Facture> {

    private final Connection con;

    public FactureService() {
        con = MyDatabse.getInstance().getCon();
    }

    @Override
    public void add(Facture facture) {
        String sql = "INSERT INTO `facture`(`idCommande`, `idUtilisateur`, `prixTotal`, `TypePaiement`, `AdresseLivraison`, `dateFacture`, `statutFacture`) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, facture.getCommande().getIdCommande());
            pstmt.setInt(2, facture.getUtilisateur().getId());
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
            pstmt.setString(2, facture.getUtilisateur().getNom()); // Utilisation de l'objet Utilisateur
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
        String sql = "SELECT * FROM facture ";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Facture facture = new Facture();
                Commande commande = new Commande();
                utilisateur user = new utilisateur();

                commande.setIdCommande(rs.getInt("idCommande"));
                user.setId(rs.getInt("idUtilisateur"));
                facture.setIdFacture(rs.getInt("idFacture"));
                facture.setCommande(commande);
                facture.setUtilisateur(user);
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
        String query = "SELECT idUtilisateur FROM user";

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
}