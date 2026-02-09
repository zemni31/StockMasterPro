package com.stock.model;

import java.io.Serializable;

/**
 * Bean représentant un produit dans le système StockMaster
 * Respecte la spécification JavaBeans
 */
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;

    // Attributs privés
    private int id;
    private String nom;
    private double prix;

    // Constructeur par défaut (OBLIGATOIRE pour JavaBeans)
    public Produit() {
    }

    // Constructeur avec paramètres (pour faciliter la création)
    public Produit(int id, String nom, double prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }

    // Getters et Setters PUBLICS (OBLIGATOIRE pour JavaBeans)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    // Méthode toString pour le débogage
    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                '}';
    }
}