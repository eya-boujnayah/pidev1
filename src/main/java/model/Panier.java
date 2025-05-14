package model;

public class Panier {
    private int idPanier;
    private produit produit;
    private int quantite;

    // Constructeur sans arguments
    public Panier() {
    }

    // Constructeur avec ID (cas de récupération depuis la base)
    public Panier(int idPanier, produit produit, int quantite) {
        this.idPanier = idPanier;
        this.produit = produit;
        this.quantite = quantite;
    }

    // Constructeur sans ID (cas d'une nouvelle entrée)
    public Panier(produit produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
    }

    // Getters et Setters
    public int getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
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

    @Override
    public String toString() {
        return "Panier{" +
                "idPanier=" + idPanier +
                ", produit=" + produit +
                ", quantite=" + quantite +
                '}';
    }
}
