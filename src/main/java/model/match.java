package model;

import java.util.Date;

public class match {
    private int idMatch;
    private int idTerrain;
    private int idJoueur1;
    private int idJoueur2;
    private Date dateMatch, heureDebut, heureFin ;
    private String statutMatch;

    public match() {
    }

    public match(int idMatch, int idTerrain, int idJoueur1, int idJoueur2, Date dateMatch, Date heureDebut, Date heureFin, String statutMatch) {
        this.idMatch = idMatch;
        this.idTerrain = idTerrain;
        this.idJoueur1 = idJoueur1;
        this.idJoueur2 = idJoueur2;
        this.dateMatch = dateMatch;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.statutMatch = statutMatch;
    }

    public match(int idTerrain, int idJoueur1, int idJoueur2, Date dateMatch, Date heureDebut, Date heureFin, String statutMatch) {
        this.idTerrain = idTerrain;
        this.idJoueur1 = idJoueur1;
        this.idJoueur2 = idJoueur2;
        this.dateMatch = dateMatch;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.statutMatch = statutMatch;
    }

    public int getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }

    public int getIdTerrain() {
        return idTerrain;
    }

    public void setIdTerrain(int idTerrain) {
        this.idTerrain = idTerrain;
    }

    public int getIdJoueur1() {
        return idJoueur1;
    }

    public void setIdJoueur1(int idJoueur1) {
        this.idJoueur1 = idJoueur1;
    }

    public int getIdJoueur2() {
        return idJoueur2;
    }

    public void setIdJoueur2(int idJoueur2) {
        this.idJoueur2 = idJoueur2;
    }

    public Date getDateMatch() {
        return dateMatch;
    }

    public void setDateMatch(Date dateMatch) {
        this.dateMatch = dateMatch;
    }

    public Date getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Date heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Date getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Date heureFin) {
        this.heureFin = heureFin;
    }

    public String getStatutMatch() {
        return statutMatch;
    }

    public void setStatutMatch(String statutMatch) {
        this.statutMatch = statutMatch;
    }

    @Override
    public String toString() {
        return "match{" +
                "idMatch=" + idMatch +
                ", idTerrain=" + idTerrain +
                ", idJoueur1=" + idJoueur1 +
                ", idJoueur2=" + idJoueur2 +
                ", dateMatch=" + dateMatch +
                ", heureDebut=" + heureDebut +
                ", heureFin=" + heureFin +
                ", statutMatch='" + statutMatch + '\'' +
                '}';
    }
}
