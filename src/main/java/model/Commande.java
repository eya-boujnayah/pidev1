package model;

import java.util.Date;

public class Commande {
private int idCommande,idUtilisateur;
private float prixCommande;
private String statutCommande;
private Date dateCommande;

    public Commande() {
    }

    public Commande(int idCommande, int idUtilisateur, float prixCommande, String statutCommande, Date dateCommande) {
        this.idCommande = idCommande;
        this.idUtilisateur = idUtilisateur;
        this.prixCommande = prixCommande;
        this.statutCommande = statutCommande;
        this.dateCommande = dateCommande;
    }

    public Commande(int idUtilisateur, float prixCommande, String statutCommande, Date dateCommande) {
        this.idUtilisateur = idUtilisateur;
        this.prixCommande = prixCommande;
        this.statutCommande = statutCommande;
        this.dateCommande = dateCommande;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public float getPrixCommande() {
        return prixCommande;
    }

    public void setPrixCommande(float prixCommande) {
        this.prixCommande = prixCommande;
    }

    public String getStatutCommande() {
        return statutCommande;
    }

    public void setStatutCommande(String statutCommande) {
        this.statutCommande = statutCommande;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "idCommande=" + idCommande +
                ", idUtilisateur=" + idUtilisateur +
                ", prixCommande=" + prixCommande +
                ", statutCommande='" + statutCommande + '\'' +
                ", dateCommande=" + dateCommande +
                '}';
    }
}
