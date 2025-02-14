package service;

import model.Facture;
import utils.MyDatabse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FactureService implements IService<Facture> {

    Connection con;

    public FactureService() {
        con = MyDatabse.getInstance().getCon();
    }

    @Override
    public void add(Facture facture) {
        String sql = "INSERT INTO `facture`(`idCommande`, `idUtilisateur`, `prixTotal`, `TypePaiement`, `AdresseLivraison`, `dateFacture`, `statutFacture`) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, facture.getIdCommande());
            pstmt.setInt(2, facture.getIdUtilisateur());
            pstmt.setFloat(3, facture.getPrixTotal());
            pstmt.setString(4, facture.getTypePaiement());
            pstmt.setString(5, facture.getAdresseLivraison());
            pstmt.setDate(6, new java.sql.Date(facture.getDateFacture().getTime()));
            pstmt.setString(7, facture.getStatutFacture());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding facture: " + e.getMessage());
        }
    }

    @Override
    public void update(Facture facture) {
        String sql = "UPDATE `facture` SET `idCommande` = ?, `idUtilisateur` = ?, `prixTotal` = ?, `TypePaiement` = ?, `AdresseLivraison` = ?, `dateFacture` = ?, `statutFacture` = ? WHERE `idFacture` = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, facture.getIdCommande());
            pstmt.setInt(2, facture.getIdUtilisateur());
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
            System.err.println("Error deleting facture: " + e.getMessage());
        }
    }

    @Override
    public List<Facture> display() {
        List<Facture> factures = new ArrayList<>();
        String sql = "SELECT * FROM `facture`";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Facture f = new Facture();
                f.setIdFacture(rs.getInt("idFacture"));
                f.setIdCommande(rs.getInt("idCommande"));
                f.setIdUtilisateur(rs.getInt("idUtilisateur"));
                f.setPrixTotal(rs.getFloat("prixTotal"));
                f.setTypePaiement(rs.getString("TypePaiement"));
                f.setAdresseLivraison(rs.getString("AdresseLivraison"));
                f.setDateFacture(rs.getDate("dateFacture"));
                f.setStatutFacture(rs.getString("statutFacture"));

                factures.add(f);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching factures: " + e.getMessage());
        }

        return factures;
    }
}

