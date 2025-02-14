package tn.esprit.models;

import java.util.Date;

public class Abonnement {

    // Constantes pour les types d'abonnement
    public static final String MENSUEL = "MENSUEL";
    public static final String TRIMESTRIEL = "TRIMESTRIEL";
    public static final String ANNUEL = "ANNUEL";

    // Constantes pour les statuts d'abonnement
    public static final String ACTIF = "ACTIF";
    public static final String INACTIF = "INACTIF";
    public static final String SUSPENDU = "SUSPENDU";

    private int idAbonnement;
    private int idUtilisateur;
    private String typeAbonnement;  // Chaîne de caractères, mais limitée aux valeurs prédéfinies
    private Date dateDebut;
    private Date dateFin;
    private String statusAbonnement; // Chaîne de caractères, mais limitée aux valeurs prédéfinies

    // Constructeur par défaut
    public Abonnement() {}

    // Constructeur avec tous les champs
    public Abonnement(int idAbonnement, int idUtilisateur, String typeAbonnement, Date dateDebut, Date dateFin, String statusAbonnement) {
        this.idAbonnement = idAbonnement;
        this.idUtilisateur = idUtilisateur;
        setTypeAbonnement(typeAbonnement); // Utilisation du setter pour valider la valeur
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        setStatusAbonnement(statusAbonnement); // Utilisation du setter pour valider la valeur
    }

    // Constructeur sans idAbonnement (utile pour les nouvelles créations)
    public Abonnement(int idUtilisateur, String typeAbonnement, Date dateDebut, Date dateFin, String statusAbonnement) {
        this.idUtilisateur = idUtilisateur;
        setTypeAbonnement(typeAbonnement);
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        setStatusAbonnement(statusAbonnement);
    }

    // Getters et Setters
    public int getIdAbonnement() {
        return idAbonnement;
    }

    public void setIdAbonnement(int idAbonnement) {
        this.idAbonnement = idAbonnement;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getTypeAbonnement() {
        return typeAbonnement;
    }

    public void setTypeAbonnement(String typeAbonnement) {
        // Validation du type d'abonnement
        if (typeAbonnement == null || (!typeAbonnement.equals(MENSUEL) && !typeAbonnement.equals(TRIMESTRIEL) && !typeAbonnement.equals(ANNUEL))) {
            throw new IllegalArgumentException("Type d'abonnement invalide. Les valeurs autorisées sont : MENSUEL, TRIMESTRIEL, ANNUEL.");
        }
        this.typeAbonnement = typeAbonnement;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getStatusAbonnement() {
        return statusAbonnement;
    }

    public void setStatusAbonnement(String statusAbonnement) {

        if (statusAbonnement == null || (!statusAbonnement.equals(ACTIF) && !statusAbonnement.equals(INACTIF) && !statusAbonnement.equals(SUSPENDU))) {
            throw new IllegalArgumentException("Statut d'abonnement invalide. Les valeurs autorisées sont : ACTIF, INACTIF, SUSPENDU.");
        }
        this.statusAbonnement = statusAbonnement;
    }

    // Méthode toString pour affichage
    @Override
    public String toString() {
        return "Abonnement{" +
                "idAbonnement=" + idAbonnement +
                ", idUtilisateur=" + idUtilisateur +
                ", typeAbonnement='" + typeAbonnement + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", statusAbonnement='" + statusAbonnement + '\'' +
                '}';
    }
}