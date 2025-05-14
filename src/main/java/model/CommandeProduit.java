package model;

public class CommandeProduit {
    private Commande commande;    // Clé étrangère vers Commande
    private produit produit;      // L'objet produit
    private int quantite;         // Quantité du produit dans la commande
    private float prixUnitaire;   // Prix unitaire du produit au moment de la commande

    // Constructeur sans arguments
    public CommandeProduit() {
    }

    // Constructeur avec tous les attributs
    public CommandeProduit(Commande commande, produit produit, int quantite, float prixUnitaire) {
        this.commande = commande;
        this.produit = produit;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
    }

    // Getters et Setters
    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public produit getProduit() {
        return produit;
    }

    public void setProduit(produit produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    @Override
    public String toString() {
        return "CommandeProduit{" +
                "commande=" + commande +
                ", produit=" + produit +
                ", quantite=" + quantite +
                ", prixUnitaire=" + prixUnitaire +
                '}';
    }
}
