package com.stock.controller;

import com.stock.model.Produit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet contrôleur pour afficher le catalogue de produits
 * Pattern MVC : Cette servlet agit comme CONTRÔLEUR
 */
@WebServlet(name = "CatalogueServlet", urlPatterns = {"/catalogue"})
public class CatalogueServlet extends HttpServlet {

    /**
     * Traite les requêtes GET
     * Simule la récupération de produits (sans BD pour le moment)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // === SIMULATION : Création d'une liste de produits "en dur" ===
        List<Produit> listeProduits = new ArrayList<>();

        // Ajout de 3 produits fictifs
        listeProduits.add(new Produit(1, "Clavier", 25.00));
        listeProduits.add(new Produit(2, "Souris Gamer", 45.50));
        listeProduits.add(new Produit(3, "Écran LED 24\"", 189.99));

        // === TRANSMISSION DES DONNÉES À LA VUE ===
        // La liste est placée dans la portée "request"
        request.setAttribute("listeProduits", listeProduits);

        // === REDIRECTION VERS LA VUE (JSP) ===
        // Forward : transfert côté serveur (l'URL reste /catalogue)
        request.getRequestDispatcher("/WEB-INF/vues/catalogue.jsp")
                .forward(request, response);
    }

    /**
     * Retourne une description de la servlet
     */
    @Override
    public String getServletInfo() {
        return "Servlet d'affichage du catalogue produits";
    }
}