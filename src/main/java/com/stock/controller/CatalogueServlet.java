package com.stock.controller;

import com.stock.model.Produit;
import com.stock.service.ProduitService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CatalogueServlet", urlPatterns = {"/catalogue"})
public class CatalogueServlet extends HttpServlet {

    private ProduitService produitService;

    @Override
    public void init() {
        // Initialisation du service
        produitService = new ProduitService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ✅ Récupération via Service (au lieu de créer la liste ici)
        List<Produit> listeProduits = produitService.recupererTousLesProduits();

        // Transmission à la vue
        request.setAttribute("listeProduits", listeProduits);

        // Forward vers JSP
        request.getRequestDispatcher("/WEB-INF/vues/catalogue.jsp")
                .forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet d'affichage du catalogue produits";
    }
}