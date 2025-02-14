package tn.esprit.services;

import tn.esprit.Interfaces.Iservices;
import tn.esprit.models.Abonnement;
import tn.esprit.util.Maconnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbonnementService implements Iservices<Abonnement> {

    private Connection con;

    public AbonnementService() {
        con = Maconnexion.getInstance().getCon(); // Initialisation de la connexion à la base de données
    }

    @Override
    public void add(Abonnement abonnement) {
        String sql = "INSERT INTO abonnement (idUtilisateur, typeAbonnement, dateDebut, dateFin, statusAbonnement) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, abonnement.getIdUtilisateur());
            preparedStatement.setString(2, abonnement.getTypeAbonnement());
            preparedStatement.setDate(3, new java.sql.Date(abonnement.getDateDebut().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(abonnement.getDateFin().getTime()));
            preparedStatement.setString(5, abonnement.getStatusAbonnement());

            preparedStatement.executeUpdate();
            System.out.println("Abonnement ajouté avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de l'abonnement : " + e.getMessage());
        }
    }

    @Override
    public void update(Abonnement abonnement) {
        String sql = "UPDATE abonnement SET idUtilisateur = ?, typeAbonnement = ?, dateDebut = ?, dateFin = ?, statusAbonnement = ? WHERE idAbonnement = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, abonnement.getIdUtilisateur());
            preparedStatement.setString(2, abonnement.getTypeAbonnement());
            preparedStatement.setDate(3, new java.sql.Date(abonnement.getDateDebut().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(abonnement.getDateFin().getTime()));
            preparedStatement.setString(5, abonnement.getStatusAbonnement());
            preparedStatement.setInt(6, abonnement.getIdAbonnement());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Abonnement mis à jour avec succès !");
            } else {
                System.out.println("Aucun abonnement trouvé avec l'ID spécifié.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour de l'abonnement : " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM abonnement WHERE idAbonnement = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Abonnement supprimé avec succès !");
            } else {
                System.out.println("Aucun abonnement trouvé avec l'ID spécifié.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'abonnement : " + e.getMessage());
        }
    }

    @Override
    public List<Abonnement> display() {
        String query = "SELECT * FROM abonnement";
        List<Abonnement> abonnements = new ArrayList<>();

        try (PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Abonnement abonnement = new Abonnement();
                abonnement.setIdAbonnement(resultSet.getInt("idAbonnement"));
                abonnement.setIdUtilisateur(resultSet.getInt("idUtilisateur"));
                abonnement.setTypeAbonnement(resultSet.getString("typeAbonnement"));
                abonnement.setDateDebut(resultSet.getDate("dateDebut"));
                abonnement.setDateFin(resultSet.getDate("dateFin"));
                abonnement.setStatusAbonnement(resultSet.getString("statusAbonnement"));

                abonnements.add(abonnement);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des abonnements : " + e.getMessage());
        }

        return abonnements;
    }

    @Override
    public List<Abonnement> getAll() {
        return display(); // Utilise la méthode display() pour récupérer tous les abonnements
    }

    @Override
    public Abonnement get(int id) {
        String query = "SELECT * FROM abonnement WHERE idAbonnement = ?";
        Abonnement abonnement = null;

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                abonnement = new Abonnement();
                abonnement.setIdAbonnement(resultSet.getInt("idAbonnement"));
                abonnement.setIdUtilisateur(resultSet.getInt("idUtilisateur"));
                abonnement.setTypeAbonnement(resultSet.getString("typeAbonnement"));
                abonnement.setDateDebut(resultSet.getDate("dateDebut"));
                abonnement.setDateFin(resultSet.getDate("dateFin"));
                abonnement.setStatusAbonnement(resultSet.getString("statusAbonnement"));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de l'abonnement : " + e.getMessage());
        }

        return abonnement;
    }
}