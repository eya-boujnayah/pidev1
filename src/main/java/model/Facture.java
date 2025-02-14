package model;

import java.util.Date;

public class Facture {
    private int idFacture,idCommande;
    private int idUtilisateur;
    private float prixTotal;
    private String TypePaiement;
    private String AdresseLivraison;
    private Date dateFacture;
    private String statutFacture;
    public Facture() {}

    public Facture(int idFacture, int idCommande, int idUtilisateur, float prixTotal, String typePaiement, String adresseLivraison, Date dateFacture, String statutFacture) {
        this.idFacture = idFacture;
        this.idCommande = idCommande;
        this.idUtilisateur = idUtilisateur;
        this.prixTotal = prixTotal;
        TypePaiement = typePaiement;
        AdresseLivraison = adresseLivraison;
        this.dateFacture = dateFacture;
        this.statutFacture = statutFacture;
    }

    public Facture(int idCommande, int idUtilisateur, float prixTotal, String typePaiement, String adresseLivraison, Date dateFacture, String statutFacture) {
        this.idCommande = idCommande;
        this.idUtilisateur = idUtilisateur;
        this.prixTotal = prixTotal;
        TypePaiement = typePaiement;
        AdresseLivraison = adresseLivraison;
        this.dateFacture = dateFacture;
        this.statutFacture = statutFacture;
    }

    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
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

    public float getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(float prixTotal) {
        this.prixTotal = prixTotal;
    }

    public String getTypePaiement() {
        return TypePaiement;
    }

    public void setTypePaiement(String typePaiement) {
        TypePaiement = typePaiement;
    }

    public String getAdresseLivraison() {
        return AdresseLivraison;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        AdresseLivraison = adresseLivraison;
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

    @Override
    public String toString() {
        return "Facture{" +
                "idFacture=" + idFacture +
                ", idCommande=" + idCommande +
                ", idUtilisateur=" + idUtilisateur +
                ", prixTotal=" + prixTotal +
                ", TypePaiement='" + TypePaiement + '\'' +
                ", AdresseLivraison='" + AdresseLivraison + '\'' +
                ", dateFacture=" + dateFacture +
                ", statutFacture='" + statutFacture + '\'' +
                '}';
    }
}
