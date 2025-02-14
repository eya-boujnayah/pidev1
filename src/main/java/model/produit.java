package model;

public class produit {
    private int idProduit;
    private String nom;
    private String description;
    private double prix;
    private int stock;
    private int idCategorie;

    public produit() {
    }

    public produit(int idProduit, String nom, String description, double prix, int stock, int idCategorie) {
        this.idProduit = idProduit;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.stock = stock;
        this.idCategorie = idCategorie;
    }

    public produit(String nom, String description, double prix, int stock, int idCategorie) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.stock = stock;
        this.idCategorie = idCategorie;
    }

    @Override
    public String toString() {
        return "produit{" +
                "idProduit=" + idProduit +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", stock=" + stock +
                ", idCategorie=" + idCategorie +
                '}';
    }
}
