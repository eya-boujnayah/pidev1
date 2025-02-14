package model;

public class Centre {
    private int idCentre;
    private String nom;
    private String adresse;
    private String telephone;
    private int nbTerrains;

    public Centre() {}

    public Centre(int idCentre, String nom, String adresse, String telephone, int nbTerrains) {
        this.idCentre = idCentre;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.nbTerrains = nbTerrains;
    }

    public Centre(String nom, String adresse, String telephone, int nbTerrains) {
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.nbTerrains = nbTerrains;

    }

    public int getIdCentre() {
        return idCentre;
    }

    public void setIdCentre(int idCentre) {
        this.idCentre = idCentre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getNbTerrains() {
        return nbTerrains;
    }

    public void setNbTerrains(int nbTerrains) {
        this.nbTerrains = nbTerrains;
    }



    @Override
    public String toString() {
        return "centre{" +
                "idCentre=" + idCentre +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", nbTerrains=" + nbTerrains +
                '}';
    }
}
