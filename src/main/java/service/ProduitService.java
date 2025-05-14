package service;

import model.Facture;
import model.produit;
import utils.MyDatabse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitService implements IService<produit> {
    private Connection con;

    public ProduitService() throws SQLException {
        con = MyDatabse.getInstance().getCon();
    }


    @Override
    public void add(produit produit) {

    }

    @Override
    public void update(produit produit) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<produit> display() {
        List<produit> produits = new ArrayList<>();
        String sql = "SELECT * FROM produit";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                produit p = new produit();
                p.setIdProduit(rs.getInt("idProduit"));
                p.setNom(rs.getString("nom"));
                p.setDescription(rs.getString("description"));
                p.setPrix(rs.getDouble("prix"));
                p.setStock(rs.getInt("stock"));
                p.setIdCategorie(rs.getInt("idCategorie"));
                produits.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produits;
    }


}
