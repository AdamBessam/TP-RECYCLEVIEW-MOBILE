package com.ensa.tp4.beans;

public class Player {
    private int id;
    private String nom;
    private String img;
    private float star;
    private  static int  comp;
    public Player(String nom,String img,float star){
        this.id=++comp;
        this.nom=nom;
        this.img=img;
        this.star=star;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setStar(float star) {
        this.star = star;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getImg() {
        return img;
    }

    public float getStar() {
        return star;
    }
}
