package model;

public class participation {
    private int idParticipation;
    private int idTournoi;
    private int idUtilisateur;
    private String niveau;
    private int nbTournoiJoue;

    public participation() {
    }

    public participation(int idParticipation, int idTournoi, int idUtilisateur, String niveau, int nbTournoiJoue) {
        this.idParticipation = idParticipation;
        this.idTournoi = idTournoi;
        this.idUtilisateur = idUtilisateur;
        this.niveau = niveau;
        this.nbTournoiJoue = nbTournoiJoue;
    }

    public participation(int idTournoi, int idUtilisateur, String niveau, int nbTournoiJoue) {
        this.idTournoi = idTournoi;
        this.idUtilisateur = idUtilisateur;
        this.niveau = niveau;
        this.nbTournoiJoue = nbTournoiJoue;
    }

    public int getIdParticipation() {
        return idParticipation;
    }

    public void setIdParticipation(int idParticipation) {
        this.idParticipation = idParticipation;
    }

    public int getIdTournoi() {
        return idTournoi;
    }

    public void setIdTournoi(int idTournoi) {
        this.idTournoi = idTournoi;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public int getNbTournoiJoue() {
        return nbTournoiJoue;
    }

    public void setNbTournoiJoue(int nbTournoiJoue) {
        this.nbTournoiJoue = nbTournoiJoue;
    }

    @Override
    public String toString() {
        return "participation{" +
                "idParticipation=" + idParticipation +
                ", idTournoi=" + idTournoi +
                ", idUtilisateur=" + idUtilisateur +
                ", niveau='" + niveau + '\'' +
                ", nbTournoiJoue=" + nbTournoiJoue +
                '}';
    }
}
