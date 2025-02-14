package model;

import java.util.Date;

public class Tournoi {
    private int idTournoi;
    private String titre;
    private String description;
    private int nbr;
    private Date date;
    private String trophee;

    public Tournoi() {
    }

    public Tournoi(int idTournoi, String titre, String description, int nbr, Date date, String trophee) {
        this.idTournoi = idTournoi;
        this.titre = titre;
        this.description = description;
        this.nbr = nbr;
        this.date = date;
        this.trophee = trophee;
    }

    public Tournoi(String titre, String description, int nbr, Date date, String trophee) {
        this.titre = titre;
        this.description = description;
        this.nbr = nbr;
        this.date = date;
        this.trophee = trophee;
    }

    public int getIdTournoi() {
        return idTournoi;
    }

    public void setIdTournoi(int idTournoi) {
        this.idTournoi = idTournoi;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTrophee() {
        return trophee;
    }

    public void setTrophee(String trophee) {
        this.trophee = trophee;
    }

    @Override
    public String toString() {
        return "Tournoi{" +
                "idTournoi=" + idTournoi +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", nbr=" + nbr +  // Including nbr in the string representation
                ", date=" + date +
                ", trophee='" + trophee + '\'' +
                '}';
    }
}
