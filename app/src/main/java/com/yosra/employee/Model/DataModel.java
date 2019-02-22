package com.yosra.employee.Model;

public class DataModel {

int id;
String nom, prenom, identifiant, password,date,etat;
String titre,path;

    public DataModel(int id,String titre,String path, String nom, String prenom, String identifiant, String password, String date, String etat) {
        this.id = id;
        this.nom = nom;
        this.titre=titre;
        this.path=path;
        this.prenom = prenom;
        this.identifiant = identifiant;
        this.password = password;
        this.date = date;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getPath() {
        return path;
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

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
