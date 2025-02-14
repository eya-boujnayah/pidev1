package test;

import model.Commande;
import model.Facture;
import service.CommandeService;
import service.FactureService;

import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
/*// Ajout d'une facture
        Facture f = new Facture(1,1,150.0f,"c","d",new Date(),"b");
        FactureService factureService = new FactureService();
        factureService.add(f);
        System.out.println("Facture ajoutée.");
// Affichage des factures
        System.out.println("\nListe de toutes les factures:");
        List<Facture> factures = factureService.display();
        for (Facture f1 : factures) {
            System.out.println(f1.getIdFacture() + " - " + f1.getIdCommande() + " - " + f1.getIdUtilisateur() + " - " + f1.getPrixTotal() + " - " + f1.getTypePaiement() + " - " + f1.getAdresseLivraison() + " - " + f1.getDateFacture() + " - " + f1.getStatutFacture());
        }
// Suppression d'une facture

        factureService.delete(3);
        System.out.println("\nFacture avec l'ID " + 3+ " supprimée.");
// Affichage après suppression
        System.out.println("\nListe des factures après suppression:");
        List<Facture> facturesApresSuppression = factureService.display();
        for (Facture f2 : facturesApresSuppression) {
            System.out.println(f2.getIdFacture() + " - " + f2.getIdCommande() + " - " + f2.getIdUtilisateur() + " - " + f2.getPrixTotal() + " - " + f2.getTypePaiement() + " - " + f2.getAdresseLivraison() + " - " + f2.getDateFacture() + " - " + f2.getStatutFacture());
        }
// Mise à jour d'une facture
        int idFactureAMettreAJour = 5;
        Facture factureAModifier = new Facture(idFactureAMettreAJour, 2, 200.0f, "PayPal", "456 Avenue Centrale", new Date(), "En attente");
        factureService.update(factureAModifier);
        System.out.println("\nFacture mise à jour avec succès.");*/

// Ajout d'une commande
        Commande c = new Commande(1, 250.0f, "En cours", new Date());
        CommandeService commandeService = new CommandeService();
        commandeService.add(c);
        System.out.println("Commande ajoutée.");

// Affichage des commandes
        System.out.println("\nListe de toutes les commandes:");
        List<Commande> commandes = commandeService.display();
        for (Commande c1 : commandes) {
            System.out.println(c1.getIdCommande() + " - " + c1.getIdUtilisateur() + " - " + c1.getPrixCommande() + " - " + c1.getStatutCommande() + " - " + c1.getDateCommande());
        }
// Suppression d'une commande
        int idCommandeASupprimer = 3;
        commandeService.delete(idCommandeASupprimer);
        System.out.println("\nCommande avec l'ID " + idCommandeASupprimer + " supprimée.");

// Affichage après suppression
        System.out.println("\nListe des commandes après suppression:");
        List<Commande> commandesApresSuppression = commandeService.display();
        for (Commande c2 : commandesApresSuppression) {
            System.out.println(c2.getIdCommande() + " - " + c2.getIdUtilisateur() + " - " + c2.getPrixCommande() + " - " + c2.getStatutCommande() + " - " + c2.getDateCommande());
        }
// Mise à jour d'une commande
        int idCommandeAMettreAJour = 5;
        Commande commandeAModifier = new Commande(idCommandeAMettreAJour, 2, 300.0f, "Validée", new Date());
        commandeService.update(commandeAModifier);
        System.out.println("\nCommande mise à jour avec succès.");
    }
}
