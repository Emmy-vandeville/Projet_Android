package com.example.myapplication;

import java.io.Serializable;

public class Book implements Serializable {

    private int idLivre;
    private String nomLivre;
    private String auteur;
    private String imgCouverture;
    private String id_compte;

    public Book(int idLivre, String nomLivre, String auteur, String imgCouverture, String id_compte) {
        this.idLivre = idLivre;
        this.nomLivre = nomLivre;
        this.auteur = auteur;
        this.imgCouverture = imgCouverture;
        this.id_compte = id_compte;
    }

    public Book(){

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

    public String getId_compte() {
        return id_compte;
    }

    public void setId_compte(String id_compte) {
        this.id_compte = id_compte;
    }
}


