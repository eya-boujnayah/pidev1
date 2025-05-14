package service;

import model.Commande;
import model.CommandeProduit;
import model.Utilisateur;
import model.produit;
import utils.MyDatabse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandeService implements IService<Commande> {

    Connection con;

    public CommandeService() throws SQLException {
        con = MyDatabse.getInstance().getCon();
    }

    @Override
    public void add(Commande commande) {
        String sql = "INSERT INTO commande (idUtilisateur, prixTotal, dateCommande, statutCommande) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, commande.getIdUtilisateur().getIdUtilisateur());
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
            pstmt.setInt(1, commande.getIdUtilisateur().getIdUtilisateur());
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

                Utilisateur user = new Utilisateur();
                user.setIdUtilisateur(rs.getInt("idUtilisateur"));
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


    public List<Utilisateur> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT * FROM user";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setNom(rs.getString("nom"));
                utilisateurs.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utilisateurs;
    }

    public model.Commande getCommandeById(int id) {
        for (model.Commande commande : display()) {
            if (commande.getIdCommande() == id) {
                return commande;
            }
        }
        return null;
    }

    public List<CommandeProduit> getProduitsByCommandeId(int idCommande) {
        List<CommandeProduit> commandeProduits = new ArrayList<>();
        String sql = "SELECT p.idProduit, p.nom, p.description, p.prix, cp.quantite " +
                "FROM produit p " +
                "JOIN commande_produit cp ON p.idProduit = cp.idProduit " +
                "WHERE cp.idCommande = ?";

        // Vérification si la connexion est ouverte
        try {
            if (con == null || con.isClosed()) {
                con = MyDatabse.getInstance().getCon();  // Rouvrir la connexion si elle est fermée
            }

            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, idCommande);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        // Récupération des données de la commande et du produit
                        produit prod = new produit();
                        prod.setIdProduit(rs.getInt("idProduit"));
                        prod.setNom(rs.getString("nom"));
                        prod.setDescription(rs.getString("description"));
                        prod.setPrix(rs.getDouble("prix"));

                        int quantite = rs.getInt("quantite");  // Récupération de la quantité

                        // Création de l'objet CommandeProduit et ajout à la liste
                        CommandeProduit commandeProduit = new CommandeProduit();
                        commandeProduit.setProduit(prod);
                        commandeProduit.setQuantite(quantite);
                        commandeProduit.setPrixUnitaire(prod.getPrix());  // Optionnel si vous avez besoin de prix unitaire

                        commandeProduits.add(commandeProduit);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commandeProduits;
    }


    // Méthode pour afficher la liste des produits d'une commande spécifique
    public void afficherProduitsCommande(int idCommande) {
        Commande commande = getCommandeById(idCommande);
        if (commande != null) {
            List<CommandeProduit> produits = getProduitsByCommandeId(idCommande);

            System.out.println("Liste des produits pour la commande " + idCommande + ":");
            for (CommandeProduit produit : produits) {
                System.out.println("Produit ID: " + produit.getProduit().getNom());
                System.out.println("Nom: " + produit.getPrixUnitaire());
                System.out.println("Prix: " + produit.getQuantite());
                System.out.println("---------------------------------");
            }
        } else {
            System.out.println("Commande non trouvée.");
        }
    }



//
//    public void addcommande(Commande commande, Map<produit, Integer> panier) {
//        String sqlCommande = "INSERT INTO commande (idUtilisateur, prixTotal, statutCommande, dateCommande) VALUES (?, ?, ?, ?)";
//
//        try (Connection conn = MyDatabse.getInstance().getCon();
//             PreparedStatement stmt = conn.prepareStatement(sqlCommande, Statement.RETURN_GENERATED_KEYS)) {
//
//            stmt.setInt(1, commande.getIdUtilisateur().getIdUtilisateur());
//            stmt.setFloat(2, commande.getPrixCommande());
//            stmt.setString(3, commande.getStatutCommande());
//            stmt.setDate(4, new java.sql.Date(commande.getDateCommande().getTime()));
//
//            stmt.executeUpdate();
//
//            ResultSet rs = stmt.getGeneratedKeys();
//            if (rs.next()) {
//                int idCommande = rs.getInt(1);
//
//                String sqlCommandeProduit = "INSERT INTO commande_produit (idCommande, idProduit, quantite) VALUES (?, ?, ?)";
//
//                try (PreparedStatement stmtProduit = conn.prepareStatement(sqlCommandeProduit)) {
//                    for (Map.Entry<produit, Integer> entry : panier.entrySet()) {
//                        produit prod = entry.getKey();
//                        int quantite = entry.getValue();
//
//                        stmtProduit.setInt(1, idCommande);
//                        stmtProduit.setInt(2, prod.getIdProduit());
//                        stmtProduit.setInt(3, quantite);
//                        stmtProduit.executeUpdate();
//                    }
//                }
//
//                System.out.println("✅ Commande enregistrée avec succès et produits ajoutés !");
//            }
//
//        } catch (SQLException e) {
//            System.err.println("❌ Erreur lors de l'ajout de la commande et des produits : " + e.getMessage());
//        }
//    }

    public void addcommande(Commande commande, Map<produit, Integer> panier) {
        String sql = "INSERT INTO commande (idUtilisateur, prixTotal, statutCommande, dateCommande) VALUES (?, ?, ?, ?)";

        try (Connection conn = MyDatabse.getInstance().getCon();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, commande.getIdUtilisateur().getIdUtilisateur());
            stmt.setFloat(2, commande.getPrixCommande());
            stmt.setString(3, commande.getStatutCommande());
            stmt.setDate(4, new java.sql.Date(commande.getDateCommande().getTime()));

            stmt.executeUpdate();

            System.out.println("✅ Commande enregistrée avec succès !");

        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de l'ajout de la commande : " + e.getMessage());
        }
    }

}
