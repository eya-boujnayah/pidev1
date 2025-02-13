package model;

import java.util.Date;

public class tournoi {
    private int idTournoi;
    private String titre;
    private String description;
    private Date dateDebut;
    private String trophee;

    public tournoi() {
    }

    public tournoi(int idTournoi, String titre, String description, Date dateDebut, String trophee) {
        this.idTournoi = idTournoi;
        this.titre = titre;
        this.description = description;
        this.dateDebut = dateDebut;
        this.trophee = trophee;
    }

    public tournoi(String titre, String description, Date dateDebut, String trophee) {
        this.titre = titre;
        this.description = description;
        this.dateDebut = dateDebut;
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

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getTrophee() {
        return trophee;
    }

    public void setTrophee(String trophee) {
        this.trophee = trophee;
    }

    @Override
    public String toString() {
        return "tournoi{" +
                "idTournoi=" + idTournoi +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", dateDebut=" + dateDebut +
                ", trophee='" + trophee + '\'' +
                '}';
    }
}
