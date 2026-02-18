package com.stock.dao;

import com.stock.model.Produit;
import java.util.List;

/**
 * Interface DAO pour la gestion des produits
 * Pattern : Interface/Implémentation
 * Permet le découplage et la testabilité
 */
public interface IProduitDAO {

    /**
     * Récupère tous les produits de la base de données
     * @return Liste de tous les produits
     */
    List<Produit> findAll();

    /**
     * Ajoute un nouveau produit
     * @param produit Le produit à ajouter
     */
    void add(Produit produit);

    /**
     * Récupère un produit par son ID
     * @param id L'identifiant du produit
     * @return Le produit ou null si non trouvé
     */
    Produit findById(int id);
}