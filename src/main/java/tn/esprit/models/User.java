package tn.esprit.models;

import java.util.Date;

public class User {

    private int idUtilisateur;
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private String telephone;
    private String role;
    private Date dateInscription;
    private Date dateNaissance;

    public User() {}

    public User(int idUtilisateur, String nom, String prenom, String email, String mdp, String telephone, String role, Date dateInscription, Date dateNaissance) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.telephone = telephone;
        setRole(role);
        this.dateInscription = dateInscription;
        this.dateNaissance = dateNaissance;
    }

    public User(String nom, String prenom, String email, String mdp, String telephone, String role, Date dateInscription, Date dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.telephone = telephone;
        this.role = role;
        this.dateInscription = dateInscription;
        this.dateNaissance = dateNaissance;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role.equals("a") || role.equals("j") || role.equals("co")) {
            this.role = role;
        } else {
            throw new IllegalArgumentException("Rôle invalide. Valeurs possibles : 'a', 'j', 'co'.");
        }
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUtilisateur=" + idUtilisateur +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", mdp='" + mdp + '\'' +
                ", telephone='" + telephone + '\'' +
                ", role='" + role + '\'' +
                ", dateInscription=" + dateInscription +
                ", dateNaissance=" + dateNaissance +
                '}';
    }
}
