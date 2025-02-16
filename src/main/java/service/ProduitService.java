package service;
import model.produit;
import utils.MyDatabse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitService implements IService<produit>{
    Connection con ;
    public ProduitService(){
        con=MyDatabse.getInstance().getCon();
    }
    @Override
    public void add(produit produit) {
        String sql = "INSERT INTO `produit`(`reference`, `nom`, `description`, `status`, `marque`, `prix`, `stock`, `couleurs`, `imagepath`, `idCategorie`) " +
                "VALUES ('"+produit.getReference()+"', '"+produit.getNom()+"', '"+produit.getDescription()+"', '"+produit.getStatus()+"', '"+produit.getMarque()+"', " +
                "'"+produit.getPrix()+"', '"+produit.getStock()+"', '"+produit.getCouleurs()+"', '"+produit.getImagepath()+"','"+produit.getIdCategorie()+"')";// `idCategorie` est généré automatiquement

        try {
            Statement statement = con.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    @Override
    public void update(produit produit) {
        String sql = "UPDATE `produit` SET `reference` = ?, `nom` = ?, `description` = ?, `status` = ?, `marque` = ?, " +
                "`prix` = ?, `stock` = ?, `couleurs` = ?, `imagepath` = ?, `idCategorie` = ? WHERE `idProduit` = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, produit.getReference());
            preparedStatement.setString(2, produit.getNom());
            preparedStatement.setString(3, produit.getDescription());
            preparedStatement.setString(4, produit.getStatus());
            preparedStatement.setString(5, produit.getMarque());
            preparedStatement.setDouble(6, produit.getPrix());
            preparedStatement.setInt(7, produit.getStock());
            preparedStatement.setString(8, produit.getCouleurs());
            preparedStatement.setString(9, produit.getImagepath());
            preparedStatement.setInt(10, produit.getIdCategorie());
            preparedStatement.setInt(11, produit.getIdProduit());  // L'ID du produit à mettre à jour

            // Exécution de la mise à jour
            preparedStatement.executeUpdate();
            System.out.println("Produit mis à jour avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour du produit : " + e.getMessage());
        }
    }




    @Override
    public  void delete(int id)
    {
        String sql = "DELETE FROM produit WHERE idProduit = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting produit: " + e.getMessage());
        }
    }
    public List<produit> display() {
        String query = "SELECT * FROM `produit`";
        List<produit> produits = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                produit p = new produit();

                p.setIdProduit(rs.getInt("idProduit")); // Assure-toi que c'est le bon nom dans ta table
                p.setReference(rs.getString("reference"));
                p.setNom(rs.getString("nom"));
                p.setDescription(rs.getString("description"));
                p.setStatus(rs.getString("status"));
                p.setMarque(rs.getString("marque"));
                p.setPrix(rs.getDouble("prix"));
                p.setStock(rs.getInt("stock"));
                p.setCouleurs(rs.getString("couleurs"));
                p.setImagepath(rs.getString("imagepath"));
                p.setIdCategorie(rs.getInt("idCategorie"));

                produits.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des produits : " + e.getMessage());
        }

        return produits;
    }


}
