package model;

public class categorie {
    private int idCategorie;
    private String nom;

    public categorie() {
    }

    public categorie(String nom) {
        this.nom = nom;
    }

    public categorie(int idCategorie, String nom) {
        this.idCategorie = idCategorie;
        this.nom = nom;
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

    @Override
    public String toString() {
        return "categorie{" +
                "idCategorie=" + idCategorie +
                ", nom='" + nom + '\'' +
                '}';
    }
}
