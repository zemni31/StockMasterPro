package com.stock.service;

import com.stock.model.Produit;
import java.util.List;

/**
 * Interface Service pour la logique métier
 * Pattern : Interface/Implémentation
 */
public interface IStockService {

    /**
     * Récupère tous les produits (avec logique métier si nécessaire)
     * @return Liste des produits
     */
    List<Produit> recupererTousLesProduits();

    /**
     * Récupère les produits filtrés (exemple de logique métier)
     * @return Liste des produits en stock
     */
    List<Produit> recupererCatalogueFiltre();
}