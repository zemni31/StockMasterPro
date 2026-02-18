package com.stock.service.impl;

import com.stock.dao.IProduitDAO;
import com.stock.dao.impl.ProduitDAOImpl;
import com.stock.model.Produit;
import com.stock.service.IStockService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implémentation du service métier
 * Contient la logique métier et appelle le DAO
 */
public class StockServiceImpl implements IStockService {

    // Le service utilise l'INTERFACE DAO (pas l'implémentation directement)
    private IProduitDAO produitDAO;

    /**
     * Constructeur : Injection de dépendance manuelle
     */
    public StockServiceImpl() {
        this.produitDAO = new ProduitDAOImpl();
    }

    /**
     * Récupère tous les produits
     */
    @Override
    public List<Produit> recupererTousLesProduits() {
        return produitDAO.findAll();
    }

    /**
     * Exemple de logique métier : Filtrer les produits
     * (Par exemple, ne garder que ceux > 50€)
     */
    @Override
    public List<Produit> recupererCatalogueFiltre() {
        List<Produit> tousProduits = produitDAO.findAll();

        // Logique métier : filtrer les produits chers
        return tousProduits.stream()
                .filter(p -> p.getPrix() > 50.0)
                .collect(Collectors.toList());
    }
}