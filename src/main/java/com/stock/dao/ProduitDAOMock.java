package com.stock.dao;

import com.stock.model.Produit;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO Mock - Simulation sans base de données
 */
public class ProduitDAOMock {

    public List<Produit> getAllProduits() {
        List<Produit> produits = new ArrayList<>();
        produits.add(new Produit(1, "Clavier", 25.00));
        produits.add(new Produit(2, "Souris Gamer", 45.50));
        produits.add(new Produit(3, "Écran LED 24\"", 189.99));
        return produits;
    }
}