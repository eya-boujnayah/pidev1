package model;

import java.util.List;

public class produit {
    private int idProduit;
    private String nom;
    private String description;
    private double prix;
    private int stock;
    private int idCategorie;
    private List<CommandeProduit> commandes; // Liste des commandes contenant ce produit

    public produit() {
    }
    public produit(int idProduit, String nom, double prix, int stock) {
        this.idProduit = idProduit;
        this.nom = nom;
        this.prix = prix;
        this.stock = stock;
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

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return (float) prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public List<CommandeProduit> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<CommandeProduit> commandes) {
        this.commandes = commandes;
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
