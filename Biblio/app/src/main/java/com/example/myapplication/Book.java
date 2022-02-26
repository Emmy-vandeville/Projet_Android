package com.example.myapplication;

import java.io.Serializable;

public class Book implements Serializable {

    private int idLivre;
    private String nomLivre;
    private String auteur;
    private String imgCouverture;
    private boolean statut;
    private String date;

    public Book(int idLivre, String nomLivre, String auteur, String imgCouverture, boolean statut, String date) {
        this.idLivre = idLivre;
        this.nomLivre = nomLivre;
        this.auteur = auteur;
        this.imgCouverture = imgCouverture;
        this.statut = statut;
        this.date = date;
    }

    public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public String getNomLivre() {
        return nomLivre;
    }

    public void setNomLivre(String nomLivre) {
        this.nomLivre = nomLivre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getImgCouverture() {
        return imgCouverture;
    }

    public void setImgCouverture(String imgCouverture) {
        this.imgCouverture = imgCouverture;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}


