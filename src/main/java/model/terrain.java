package model;

public class terrain {
    private int idTerrain;
    private int idCentre;
    private boolean disponibilite;
    private double prix;

    public terrain() {
    }

    public terrain(int idTerrain, int idCentre, boolean disponibilite, double prix) {
        this.idTerrain = idTerrain;
        this.idCentre = idCentre;
        this.disponibilite = disponibilite;
        this.prix = prix;
    }

    public terrain(int idCentre, boolean disponibilite, double prix) {
        this.idCentre = idCentre;
        this.disponibilite = disponibilite;
        this.prix = prix;
    }

    public int getIdTerrain() {
        return idTerrain;
    }

    public void setIdTerrain(int idTerrain) {
        this.idTerrain = idTerrain;
    }

    public int getIdCentre() {
        return idCentre;
    }

    public void setIdCentre(int idCentre) {
        this.idCentre = idCentre;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public boolean isDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    @Override
    public String toString() {
        return "terrain{" +
                "idTerrain=" + idTerrain +
                ", idCentre=" + idCentre +
                ", disponibilite=" + disponibilite +
                ", prix=" + prix +
                '}';
    }
}
