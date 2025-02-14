package model;

import java.util.Date;

public class abonnement {
    int idAbonnement;
    int idUtilisateur;
    Date dateDebut;
    Date dateFin;

    public abonnement() {
    }

    public abonnement(int idUtilisateur, Date dateDebut, Date dateFin) {
        this.idUtilisateur = idUtilisateur;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public abonnement(int idAbonnement, int idUtilisateur, Date dateDebut, Date dateFin) {
        this.idAbonnement = idAbonnement;
        this.idUtilisateur = idUtilisateur;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "abonnement{" +
                "idAbonnement=" + idAbonnement +
                ", idUtilisateur=" + idUtilisateur +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                '}';
    }
}
