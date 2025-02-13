package model;

import java.util.Date;

public class categorie {
    private int idCategorie;
    private String nom,description,status;
private Date   date_creation;

    @Override
    public String toString() {
        return "categorie{" +
                "idCategorie=" + idCategorie +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", date_creation=" + date_creation +
                '}';
    }

    public categorie() {
    }


    public categorie(int idCategorie, String nom, String description, String status, Date date_creation) {
        this.idCategorie = idCategorie;
        this.nom = nom;
        this.description = description;
        this.status = status;
        this.date_creation = date_creation;

    }
    public categorie(String nom, String description, String status, Date date_creation) {
         this.nom = nom;
        this.description = description;
        this.status = status;
        this.date_creation = date_creation;

    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }
}
