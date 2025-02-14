package test;

<<<<<<< HEAD
import model.Tournoi;
import model.Participation;
import service.TournoiService;
import service.ParticipationService;
=======
import model.Centre;
import model.Personne;
import service.CentreService;
import service.PersonneService;
import utils.MyDatabse;
>>>>>>> 0d7d319c8522df9b743469e316fcc962f832a3a6

import java.util.List;

public class Main {

    public static void main(String[] args) {
<<<<<<< HEAD
       /* // Add a new tournoi
        Tournoi t = new Tournoi("Tournoi A", "Description du tournoi", 10, new java.util.Date(), "Trophée A");
        TournoiService tournoiService = new TournoiService();

        // Adding a tournament
        tournoiService.add(t);
        System.out.println("Tournoi ajouté");

        // Display all tournois
        System.out.println("\nListe de tous les tournois:");
        List<Tournoi> tournois = tournoiService.display();
        for (Tournoi t1 : tournois) {
            System.out.println(t1.getIdTournoi() + " - " + t1.getTitre() + " - " + t1.getDescription());
        }

        // Deleting a tournoi
        tournoiService.delete(4);
        System.out.println("\nTournoi avec l'ID 4 supprimé.");

        // Display all tournois after deletion
        System.out.println("\nListe de tous les tournois après suppression:");
        List<Tournoi> tournois2 = tournoiService.display();
        for (Tournoi t2 : tournois2) {
            System.out.println(t2.getIdTournoi() + " - " + t2.getTitre() + " - " + t2.getDescription() + " - " + t2.getNbr());
        }*/

        // Add a new participation
        Participation p = new Participation(1, 1, "Débutant", 5);  // Example IDs for the tournament and user
        ParticipationService participationService = new ParticipationService();

        // Adding a participation
        participationService.add(p);
        System.out.println("\nParticipation ajoutée");

        // Display all participations
        System.out.println("\nListe de toutes les participations:");
        List<Participation> participations = participationService.display();
        for (Participation p1 : participations) {
            System.out.println(p1.getIdParticipation() + " - " + p1.getIdTournoi() + " - " + p1.getIdUtilisateur() + " - " + p1.getNiveau());
        }

        // Deleting a participation
        participationService.delete(2);
        System.out.println("\nParticipation avec l'ID 2 supprimée.");

        // Display all participations after deletion
        System.out.println("\nListe de toutes les participations après suppression:");
        List<Participation> participations2 = participationService.display();
        for (Participation p2 : participations2) {
            System.out.println(p2.getIdParticipation() + " - " + p2.getIdTournoi() + " - " + p2.getIdUtilisateur() + " - " + p2.getNiveau());
        }

        // Update a participation
        Participation participationToUpdate = new Participation(4, 1, "debutant", 10);  // Example ID
        participationToUpdate.setIdParticipation(4);
        participationService.update(participationToUpdate);
        System.out.println("\nParticipation mise à jour avec succès.");
=======
//add
        Centre c = new Centre("a","b","23456789",5);
        CentreService centreService = new CentreService();

        centreService.add(c);

        System.out.println("mriguel");
//display
        System.out.println("\nListe de tous les centres:");
        List<Centre> centres = centreService.display();
        for (Centre c1 : centres) {
            System.out.println(c1.getIdCentre() + " - " + c1.getNom() + " - " + c1.getAdresse());
        }
//suppression
        centreService.delete(3);
        System.out.println("\nCentre avec l'ID 3 supprimé.");

        //display
        System.out.println("\nListe de tous les centres après suppressions :");
        List<Centre> centres2 = centreService.display();
        for (Centre c2 : centres2) {
            System.out.println(c2.getIdCentre() + " - " + c2.getNom() + " - " + c2.getAdresse()+" - "+c2.getNom());
        }

        //mettre a jour
Centre centreT = new Centre(5,"a","b","23456789",5);
        if (centreT != null) {
            centreT.setNom("C");
            centreT.setAdresse("d");
            centreT.setTelephone("12345678");
            centreService.update(centreT);
            System.out.println("\nCentre mis à jour avec succès.");
        } else {
            System.out.println("\nImpossible de mettre à jour le centre : centre non trouvé.");
        }
>>>>>>> 0d7d319c8522df9b743469e316fcc962f832a3a6
    }
}
