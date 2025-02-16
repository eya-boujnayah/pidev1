package model;

import java.util.Date;

public class categorie {

    private int idCategorie;
    private String reference;
    private String nom;
    private String description;
    private Date dateCreation;  // Change type from `date` to `java.util.Date`

    public categorie() {
    }

    public categorie(int idCategorie, String reference, String nom, String description, Date dateCreation) {
        this.idCategorie = idCategorie;
        this.reference = reference;
        this.nom = nom;
        this.description = description;
        this.dateCreation = dateCreation;
    }

    public categorie(String reference, String nom, String description, Date dateCreation) {
        this.reference = reference;
        this.nom = nom;
        this.description = description;
        this.dateCreation = dateCreation;
    }

    @Override
    public String toString() {
        return "categorie{" +
                "idCategorie=" + idCategorie +
                ", reference='" + reference + '\'' +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", dateCreation=" + dateCreation +  // Corrected this line
                '}';
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
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

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

}
