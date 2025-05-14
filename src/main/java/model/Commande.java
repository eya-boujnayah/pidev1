package model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Commande {
    private int idCommande;
    private Utilisateur idUtilisateur;
    private float prixCommande;
    private String statutCommande;
    private Date dateCommande;
    private List<CommandeProduit> produits;
    // Constructeur sans arguments
    public Commande() {
    }

    public Commande(int idCommande, Utilisateur idUtilisateur, float prixCommande, String statutCommande, Date dateCommande) {
        this.idCommande = idCommande;
        this.idUtilisateur = idUtilisateur;
        this.prixCommande = prixCommande;
        this.statutCommande = statutCommande;
        this.dateCommande = dateCommande;
    }

    public Commande(Utilisateur idUtilisateur, float prixCommande, String statutCommande, Date dateCommande) {
        this.idUtilisateur = idUtilisateur;
        this.prixCommande = prixCommande;
        this.statutCommande = statutCommande;
        this.dateCommande = dateCommande;
    }

    public Commande(int idCommande, int i, float prixCommande, String text, java.sql.Date date) {
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
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


    public List<CommandeProduit> getProduits() {
        return produits;
    }



    public Map<produit, Integer> getPanier() {
        Map<produit, Integer> panier = new HashMap<>();
        for (CommandeProduit commandeProduit : produits) {
            panier.put(commandeProduit.getProduit(), commandeProduit.getQuantite());
        }
        return panier;
    }


}
