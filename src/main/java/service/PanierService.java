package service;

import model.Commande;
import model.Facture;
import model.produit;
import utils.MyDatabse;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public class PanierService {

    private Map<produit, Integer> panier = new HashMap<>();
    private Connection con;

    public PanierService() throws SQLException {
        con = MyDatabse.getInstance().getCon();
    }

    public void ajouterAuPanier(produit produit, int quantite) {
        if (quantite > 0 && quantite <= produit.getStock()) {
            panier.put(produit, quantite);
            enregistrerEnBase(produit, quantite);
            System.out.println("✅ " + quantite + " x " + produit.getNom() + " ajouté au panier.");
        } else {
            System.out.println("❌ Quantité invalide !");
        }
    }

    private void enregistrerEnBase(produit produit, int quantite) {
        String query = "INSERT INTO panier (produit, quantite) VALUES (?, ?) ON DUPLICATE KEY UPDATE quantite = quantite + ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, produit.getIdProduit());
            stmt.setInt(2, quantite);
            stmt.setInt(3, quantite);
            stmt.executeUpdate();
            System.out.println("🗄️ Ajouté en base : " + quantite + " x " + produit.getNom());
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout en base : " + e.getMessage());
        }
    }

    public Map<produit, Integer> getPanier() {
        Map<produit, Integer> panierBD = new HashMap<>();
        String query = "SELECT p.idProduit, p.nom, p.prix, p.stock, panier.quantite " +
                "FROM panier " +
                "JOIN produit p ON panier.produit = p.idProduit";

        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idProduit = rs.getInt("idProduit");
                String nom = rs.getString("nom");
                double prix = rs.getDouble("prix");
                int stock = rs.getInt("stock");
                int quantite = rs.getInt("quantite");

                produit prod = new produit(idProduit, nom, prix, stock);
                panierBD.put(prod, quantite);
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la récupération du panier : " + e.getMessage());
        }
        return panierBD;
    }

    public double getTotalPanier() {
        double total = 0;
        Map<produit, Integer> panierActuel = getPanier();

        System.out.println("🔄 Calcul du total du panier...");

        for (Map.Entry<produit, Integer> entry : panierActuel.entrySet()) {
            produit prod = entry.getKey();
            int quantite = entry.getValue();
            double prixTotal = prod.getPrix() * quantite;

            System.out.println("🛒 " + quantite + " x " + prod.getNom() + " (" + prod.getPrix() + " DT) = " + prixTotal + " DT");

            total += prixTotal; // Ajouter au total
        }

        System.out.println("💰 Total Panier Final : " + total + " DT");
        return total;
    }

    public void supprimerDuPanier(int idProduit) {
        String sql = "DELETE FROM panier WHERE produit = ?"; // Supprime seulement ce produit du panier

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idProduit);
            stmt.executeUpdate();

            panier.entrySet().removeIf(entry -> entry.getKey().getIdProduit() == idProduit);

            System.out.println("🗑️ Produit ID " + idProduit + " supprimé du panier !");
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la suppression du produit du panier : " + e.getMessage());
        }
    }


    public void viderPanier() {
        panier.clear();
        System.out.println("🗑️ Panier vidé !");
    }


    public void addcommande(Commande commande, Map<produit, Integer> panier) {
        String sqlCommande = "INSERT INTO commande (idUtilisateur, prixTotal, statutCommande, dateCommande) VALUES (?, ?, ?, ?)";
        String sqlCommandeProduit = "INSERT INTO commande_produit (idCommande, idProduit, quantite) VALUES (?, ?, ?)";

        try (Connection conn = MyDatabse.getInstance().getCon()) {
            conn.setAutoCommit(false); // Démarrer la transaction

            // Insertion de la commande
            try (PreparedStatement stmtCommande = conn.prepareStatement(sqlCommande, Statement.RETURN_GENERATED_KEYS)) {
                stmtCommande.setInt(1, commande.getIdUtilisateur().getIdUtilisateur());
                stmtCommande.setFloat(2, commande.getPrixCommande());
                stmtCommande.setString(3, commande.getStatutCommande());
                stmtCommande.setDate(4, new java.sql.Date(commande.getDateCommande().getTime()));

                int affectedRows = stmtCommande.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("❌ Échec de l'insertion de la commande !");
                }

                // Récupérer l'ID généré pour la commande
                try (ResultSet rs = stmtCommande.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idCommande = rs.getInt(1);
                        commande.setIdCommande(idCommande); // Mettre à jour l'ID de la commande
                        System.out.println("✅ Commande insérée avec ID : " + idCommande);

                        // Insertion des produits dans commande_produit
                        try (PreparedStatement stmtProduit = conn.prepareStatement(sqlCommandeProduit)) {
                            for (Map.Entry<produit, Integer> entry : panier.entrySet()) {
                                produit prod = entry.getKey();
                                int quantite = entry.getValue();

                                stmtProduit.setInt(1, idCommande);
                                stmtProduit.setInt(2, prod.getIdProduit());
                                stmtProduit.setInt(3, quantite);
                                stmtProduit.addBatch(); // Ajout au batch pour optimisation

                                System.out.println("🛒 Ajout du produit " + prod.getNom() + " (ID: " + prod.getIdProduit() + ") x" + quantite);
                            }

                            stmtProduit.executeBatch(); // Exécuter le batch
                        }

                        // Tout s'est bien passé, on valide la transaction
                        conn.commit();
                        System.out.println("✅ Commande enregistrée avec succès et produits ajoutés !");
                    } else {
                        throw new SQLException("❌ Erreur : Impossible de récupérer l'ID de la commande !");
                    }
                }
            } catch (SQLException e) {
                conn.rollback(); // Annuler en cas d'erreur
                System.err.println("❌ Erreur lors de l'ajout de la commande : " + e.getMessage());
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur de connexion à la base de données : " + e.getMessage());
        }
    }


    public void add(Facture facture) throws SQLException {
        String sql = "INSERT INTO `facture`(`idCommande`, `idUtilisateur`, `prixTotal`, `TypePaiement`, `AdresseLivraison`, `dateFacture`, `statutFacture`) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = MyDatabse.getInstance().getCon()) {
            if (conn == null || conn.isClosed()) {
                throw new SQLException("❌ La connexion à la base de données est fermée !");
            }

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, facture.getCommande().getIdCommande());
                pstmt.setInt(2, facture.getUtilisateur().getIdUtilisateur());
                pstmt.setFloat(3, facture.getPrixTotal());
                pstmt.setString(4, facture.getTypePaiement());
                pstmt.setString(5, facture.getAdresseLivraison());
                pstmt.setDate(6, new java.sql.Date(facture.getDateFacture().getTime()));
                pstmt.setString(7, facture.getStatutFacture());

                pstmt.executeUpdate();
                System.out.println("✅ Facture ajoutée avec succès !");


            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de l'ajout de la facture : " + e.getMessage());
            throw e;
        }
    }


}
