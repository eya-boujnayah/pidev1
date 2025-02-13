package test;

import model.Centre;
import model.Personne;
import service.CentreService;
import service.PersonneService;
import utils.MyDatabse;

import java.util.List;

public class Main {

    public static void main(String[] args) {
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
    }
}
