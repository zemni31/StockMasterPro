package com.stock.dao.impl;

import com.stock.dao.IProduitDAO;
import com.stock.model.Produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation JDBC de l'interface IProduitDAO
 * Gère l'accès aux données des produits
 */
public class ProduitDAOImpl implements IProduitDAO {

    // === CONFIGURATION BASE DE DONNÉES ===

    // Pour MySQL :
    private static final String URL = "jdbc:mysql://localhost:3306/stockmaster_db";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Votre mot de passe MySQL



    /**
     * Obtient une connexion à la base de données
     */
    private Connection getConnection() throws SQLException {
        try {
            // Charger le driver (MySQL)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Pour H2 :
            // Class.forName("org.h2.Driver");

            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC non trouvé", e);
        }
    }

    /**
     * Récupère tous les produits de la base de données
     */
    @Override
    public List<Produit> findAll() {
        List<Produit> produits = new ArrayList<>();
        String sql = "SELECT id, nom, prix FROM produits";

        // Try-with-resources : fermeture automatique des ressources
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Parcourir les résultats
            while (rs.next()) {
                // Extraire les données de chaque ligne
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                double prix = rs.getDouble("prix");

                // Créer un objet Produit
                Produit produit = new Produit(id, nom, prix);

                // Ajouter à la liste
                produits.add(produit);
            }

            System.out.println("✅ " + produits.size() + " produits récupérés de la BD");

        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la récupération des produits");
            e.printStackTrace();
        }

        return produits;
    }

    /**
     * Ajoute un nouveau produit
     */
    @Override
    public void add(Produit produit) {
        String sql = "INSERT INTO produits (nom, prix) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produit.getNom());
            stmt.setDouble(2, produit.getPrix());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✅ Produit ajouté : " + produit.getNom());
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de l'ajout du produit");
            e.printStackTrace();
        }
    }

    /**
     * Récupère un produit par son ID
     */
    @Override
    public Produit findById(int id) {
        String sql = "SELECT id, nom, prix FROM produits WHERE id = ?";
        Produit produit = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    produit = new Produit(
                            rs.getInt("id"),
                            rs.getString("nom"),
                            rs.getDouble("prix")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la recherche du produit");
            e.printStackTrace();
        }

        return produit;
    }
}