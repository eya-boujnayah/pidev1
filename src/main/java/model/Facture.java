package model;

import java.time.LocalDate;
import java.util.Date;

public class Facture {
    private int idFacture;
    private Commande commande;  // Clé étrangère remplacée par l'objet Commande
    private Utilisateur utilisateur; // Clé étrangère remplacée par l'objet Utilisateur
    private float prixTotal;
    private String typePaiement, adresseLivraison, statutFacture;
    private Date dateFacture;

    public Facture(int idFacture, Commande commande, Utilisateur utilisateur, float prixTotal, String typePaiement, String adresseLivraison, Date dateFacture, String statutFacture) {
        this.idFacture = idFacture;
        this.commande = commande;
        this.utilisateur = utilisateur;
        this.prixTotal = prixTotal;
        this.typePaiement = typePaiement;
        this.adresseLivraison = adresseLivraison;
        this.dateFacture = dateFacture;
        this.statutFacture = statutFacture;
    }

    public Facture(Commande commande, Utilisateur utilisateur, float prixTotal, String typePaiement, String adresseLivraison, Date dateFacture, String statutFacture) {
        this.commande = commande;
        this.utilisateur = utilisateur;
        this.prixTotal = prixTotal;
        this.typePaiement = typePaiement;
        this.adresseLivraison = adresseLivraison;
        this.dateFacture = dateFacture;
        this.statutFacture = statutFacture;
    }


    public Facture() {
    }

    public Facture(int commandeId, int utilisateurId, float prix, String paiement, String adresse, String statut, String string) {
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public float getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(float prixTotal) {
        this.prixTotal = prixTotal;
    }

    public String getTypePaiement() {
        return typePaiement;
    }

    public void setTypePaiement(String typePaiement) {
        this.typePaiement = typePaiement;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public Date getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(Date dateFacture) {
        this.dateFacture = dateFacture;
    }

    public String getStatutFacture() {
        return statutFacture;
    }

    public void setStatutFacture(String statutFacture) {
        this.statutFacture = statutFacture;
    }

    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }



}
