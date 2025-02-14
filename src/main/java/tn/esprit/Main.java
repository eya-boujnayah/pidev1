package tn.esprit;

import tn.esprit.services.AbonnementService;
import tn.esprit.services.Userservice;
import tn.esprit.util.Maconnexion;
import tn.esprit.models.User;
import tn.esprit.models.Abonnement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Création d'une instance de la connexion (Singleton)
        Maconnexion mac1 = Maconnexion.getInstance();
        Maconnexion mac2 = Maconnexion.getInstance();

        // Création des services utilisateur et abonnement
        Userservice userService = new Userservice();
        AbonnementService abonnementService = new AbonnementService();

        // Définition du format de la date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            // Parsing des dates pour les utilisateurs
            Date dateInscription = dateFormat.parse("12/02/2025");
            Date dateNaissance = dateFormat.parse("12/10/2001");

            // Création d'un utilisateur
            User user = new User("Aidi", "Hatem", "hatemaidi09@gmail.com", "06021977", "92340748", "a", dateInscription, dateNaissance);

            // Ajout de l'utilisateur
            userService.add(user);
            System.out.println("Utilisateur ajouté avec succès !");

            // Mise à jour de l'utilisateur
            user.setNom("Aidi");
            user.setPrenom("Oussema");
            userService.update(user);
            System.out.println("Utilisateur mis à jour avec succès !");

            // Suppression de l'utilisateur avec l'ID 1
            userService.delete(1);
            System.out.println("\nUtilisateur avec l'ID 1 supprimé.");

            // Parsing des dates pour les abonnements
            Date dateDebut = dateFormat.parse("01/03/2025");
            Date dateFin = dateFormat.parse("01/06/2025");

            // Création d'un abonnement
            Abonnement abonnement = new Abonnement(1, Abonnement.TRIMESTRIEL, dateDebut, dateFin, Abonnement.ACTIF);

            // Ajout de l'abonnement
            abonnementService.add(abonnement);
            System.out.println("Abonnement ajouté avec succès !");

            // Mise à jour de l'abonnement
            abonnement.setTypeAbonnement(Abonnement.ANNUEL);
            abonnement.setStatusAbonnement(Abonnement.SUSPENDU);
            abonnementService.update(abonnement);
            System.out.println("Abonnement mis à jour avec succès !");

            // Suppression d'un abonnement avec l'ID 1
            abonnementService.delete(1);
            System.out.println("\nAbonnement avec l'ID 1 supprimé.");

        } catch (ParseException e) {
            // Gestion des erreurs de format de date
            System.err.println("Erreur de format de date : " + e.getMessage());
        }

        // Affichage de la liste des utilisateurs
        System.out.println("\nAffichage des utilisateurs : ");
        List<User> users = userService.display();
        for (User u : users) {
            System.out.println(
                    "ID: " + u.getIdUtilisateur() + " - " +
                            "Nom: " + u.getNom() + " - " +
                            "Prénom: " + u.getPrenom() + " - " +
                            "Email: " + u.getEmail() + " - " +
                            "Téléphone: " + u.getTelephone() + " - " +
                            "Rôle: " + u.getRole() + " - " +
                            "Date d'inscription: " + dateFormat.format(u.getDateInscription()) + " - " +
                            "Date de naissance: " + dateFormat.format(u.getDateNaissance())
            );
        }

        // Affichage de la liste des abonnements
        System.out.println("\nAffichage des abonnements : ");
        List<Abonnement> abonnements = abonnementService.display();
        for (Abonnement a : abonnements) {
            System.out.println(
                    "ID: " + a.getIdAbonnement() + " - " +
                            "Utilisateur ID: " + a.getIdUtilisateur() + " - " +
                            "Type: " + a.getTypeAbonnement() + " - " +
                            "Date début: " + dateFormat.format(a.getDateDebut()) + " - " +
                            "Date fin: " + dateFormat.format(a.getDateFin()) + " - " +
                            "Statut: " + a.getStatusAbonnement()
            );
        }
    }
}