package com.stock.service;

import com.stock.dao.ProduitDAOMock;
import com.stock.model.Produit;
import java.util.List;

/**
 * Couche Service - Logique métier
 */
public class ProduitService {

    private ProduitDAOMock dao;

    public ProduitService() {
        this.dao = new ProduitDAOMock();
    }

    public List<Produit> recupererTousLesProduits() {
        return dao.getAllProduits();
    }
}