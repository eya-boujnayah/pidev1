package model;

import java.util.Date;

public class utilisateur {
    int id;
    String cin;
    String nom;
    String prenom;
    String adresseEmail;
    String motDePasse;
    String telephone;
    String role;
    Date dateInscription;
    Date dateNaissance;

    public utilisateur() {
    }

    public utilisateur(String cin, String nom, String prenom, String adresseEmail, String motDePasse, String role, String telephone, Date dateInscription, Date dateNaissance) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.adresseEmail = adresseEmail;
        this.motDePasse = motDePasse;
        this.role = role;
        this.telephone = telephone;
        this.dateInscription = dateInscription;
        this.dateNaissance = dateNaissance;
    }

    public utilisateur(int id, String cin, String nom, String prenom, String adresseEmail, String motDePasse, String telephone, String role, Date dateInscription, Date dateNaissance) {
        this.id = id;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.adresseEmail = adresseEmail;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
        this.role = role;
        this.dateInscription = dateInscription;
        this.dateNaissance = dateNaissance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresseEmail() {
        return adresseEmail;
    }

    public void setAdresseEmail(String adresseEmail) {
        this.adresseEmail = adresseEmail;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
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
        this.role = role;
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
        return "utilisateur{" +
                "id=" + id +
                ", cin='" + cin + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresseEmail='" + adresseEmail + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", role='" + role + '\'' +
                ", dateInscription=" + dateInscription +
                ", dateNaissance=" + dateNaissance +
                '}';
    }
}
