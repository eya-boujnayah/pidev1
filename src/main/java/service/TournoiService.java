package service;

import model.Tournoi;
import utils.MyDatabse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TournoiService implements IService<Tournoi> {

    Connection con;

    public TournoiService() {
        con = MyDatabse.getInstance().getCon();
    }

    @Override
    public void add(Tournoi tournoi) {
        String sql = "INSERT INTO `tournoi`(`titre`, `description`, `nbr`, `date`, `trophee`) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, tournoi.getTitre());
            pstmt.setString(2, tournoi.getDescription());
            pstmt.setInt(3, tournoi.getNbr());
            pstmt.setDate(4, new java.sql.Date(tournoi.getDate().getTime())); 
            pstmt.setString(5, tournoi.getTrophee());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding tournoi: " + e.getMessage());
        }
    }

    @Override
    public void update(Tournoi tournoi) {
        String sql = "UPDATE `tournoi` SET `titre` = ?, `description` = ?, `nbr` = ?, `date` = ?, `trophee` = ? WHERE `idTournoi` = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, tournoi.getTitre());
            pstmt.setString(2, tournoi.getDescription());
            pstmt.setInt(3, tournoi.getNbr());
            pstmt.setDate(4, new java.sql.Date(tournoi.getDate().getTime()));
            pstmt.setString(5, tournoi.getTrophee());
            pstmt.setInt(6, tournoi.getIdTournoi());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating tournoi: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM `tournoi` WHERE `idTournoi` = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting tournoi: " + e.getMessage());
        }
    }

    @Override
    public List<Tournoi> display() {
        List<Tournoi> tournois = new ArrayList<>();
        String sql = "SELECT * FROM `tournoi`";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Tournoi t = new Tournoi();
                t.setIdTournoi(rs.getInt("idTournoi"));
                t.setTitre(rs.getString("titre"));
                t.setDescription(rs.getString("description"));
                t.setNbr(rs.getInt("nbr"));
                t.setDate(rs.getDate("date"));
                t.setTrophee(rs.getString("trophee"));

                tournois.add(t);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching tournois: " + e.getMessage());
        }

        return tournois;
    }
}
