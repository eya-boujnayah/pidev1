package model;

public class produit {
    private int idProduit;
    private String nom,description,status,marque;
    private double prix;
    private int stock;
    private int idCategorie;
    private String couleurs;

    public produit() {
    }

    public produit(int idProduit, String nom, String description,String status,String marque, double prix, int stock, String couleurs,int idCategorie) {
        this.idProduit = idProduit;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.stock = stock;
        this.idCategorie = idCategorie;
        this.couleurs = couleurs;
        this.marque = marque;
        this.status = status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public double getPrix() {
        return prix;
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

    public String getCouleurs() {
        return couleurs;
    }

    public void setCouleurs(String couleurs) {
        this.couleurs = couleurs;
    }

    public produit(String nom, String description,String status,String marque, double prix, int stock, String couleurs,int idCategorie) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.stock = stock;
        this.idCategorie = idCategorie;
        this.couleurs = couleurs;
        this.marque = marque;
        this.status = status;

    }

    @Override
    public String toString() {
        return "produit{" +
                "idProduit=" + idProduit +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", marque='" + marque + '\'' +
                ", prix=" + prix +
                ", stock=" + stock +
                ", idCategorie=" + idCategorie +
                ", couleurs='" + couleurs + '\'' +
                '}';
    }

}
