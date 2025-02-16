package model;

public class produit {
    private int idProduit;
    private String reference;
    private String nom;
    private String description;
    private String marque;
    private double prix;
    private int stock;
    private String couleurs;
    private String status;
    private String imagepath;
    private int idCategorie;

    @Override
    public String toString() {
        return "produit{" +
                "idProduit=" + idProduit +
                ", reference='" + reference + '\'' +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", marque='" + marque + '\'' +
                ", prix=" + prix +
                ", stock=" + stock +
                ", couleurs='" + couleurs + '\'' +
                ", status='" + status + '\'' +
                ", imagepath='" + imagepath + '\'' +
                ", idCategorie=" + idCategorie +
                '}';
    }

    public produit() {
    }

    public produit(int idProduit, String reference, String nom, String description, String marque, double prix, int stock, String couleurs, String status, String imagepath, int idCategorie) {
        this.idProduit = idProduit;
        this.reference = reference;
        this.nom = nom;
        this.description = description;
        this.marque = marque;
        this.prix = prix;
        this.stock = stock;
        this.couleurs = couleurs;
        this.status = status;
        this.imagepath = imagepath;
        this.idCategorie = idCategorie;
    }

    public produit(String reference, String nom, String description, double prix, String marque, int stock, String couleurs, String status, String imagepath, int idCategorie) {
        this.reference = reference;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.marque = marque;
        this.stock = stock;
        this.couleurs = couleurs;
        this.status = status;
        this.imagepath = imagepath;
        this.idCategorie = idCategorie;
    }

    public produit(String reference, String nom, String description, String marque, double prix, int stock, String couleurs, String status, String imagepath) {
        this.reference = reference;
        this.nom = nom;
        this.description = description;
        this.marque = marque;
        this.prix = prix;
        this.stock = stock;
        this.couleurs = couleurs;
        this.status = status;
        this.imagepath = imagepath;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
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

    public String getCouleurs() {
        return couleurs;
    }

    public void setCouleurs(String couleurs) {
        this.couleurs = couleurs;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }
}
