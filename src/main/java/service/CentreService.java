package service;


import model.Centre;
import utils.MyDatabse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CentreService implements IService<Centre> {

    Connection con ;

   public CentreService() {
       con = MyDatabse.getInstance().getCon();

   }
    @Override
    public void add(Centre centre) {
        String sql = "INSERT INTO `centre`(`nom`, `adresse`, `telephone`, `nbTerrains`) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, centre.getNom());
            pstmt.setString(2, centre.getAdresse());
            pstmt.setString(3, centre.getTelephone());
            pstmt.setInt(4, centre.getNbTerrains());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding centre: " + e.getMessage());
        }

    }

    @Override
    public void update(Centre centre) {
        String sql = "UPDATE `centre` SET `nom` = ?, `adresse` = ?, `telephone` = ?, `nbTerrains` = ? WHERE `idCentre` = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, centre.getNom());
            pstmt.setString(2, centre.getAdresse());
            pstmt.setString(3, centre.getTelephone());
            pstmt.setInt(4, centre.getNbTerrains());
            pstmt.setInt(5, centre.getIdCentre());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating centre: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM `centre` WHERE `idCentre` = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting centre: " + e.getMessage());
        }
    }

    @Override
    public List<Centre> display() {
        List<Centre> Centres = new ArrayList<>();
        String sql = "SELECT * FROM `centre`";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Centre c = new Centre();
                c.setIdCentre(rs.getInt("idCentre"));
                c.setNom(rs.getString("nom"));
                c.setAdresse(rs.getString("adresse"));
                c.setTelephone(rs.getString("telephone"));
                c.setNbTerrains(rs.getInt("nbTerrains"));

                Centres.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching centres: " + e.getMessage());
        }

        return Centres;
    }

    }

