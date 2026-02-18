package com.stock.controller;

import com.stock.model.Produit;
import com.stock.service.IStockService;
import com.stock.service.impl.StockServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "CatalogueServlet", urlPatterns = {"/catalogue"})
public class CatalogueServlet extends HttpServlet {

    // ✅ ARCHITECTURE PROFESSIONNELLE : Utilise l'INTERFACE
    private IStockService stockService;

    @Override
    public void init() {
        // Initialisation du service (implémentation)
        stockService = new StockServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // === PARTIE 4 : GESTION DES COOKIES ===

        // 1. LECTURE : Récupérer et DÉCODER le cookie lastVisit
        String derniereVisite = null;//derniereVisite = Ce qui était dans le cookie (t-1) → On affiche
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("lastVisit".equals(cookie.getName())) {
                    // ✅ DÉCODAGE de la valeur du cookie
                    derniereVisite = URLDecoder.decode(
                            cookie.getValue(),
                            StandardCharsets.UTF_8
                    );
                    break;
                }
            }
        }

        // 2. ÉCRITURE : Créer/Mettre à jour le cookie avec la date actuelle
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateActuelle = dateFormat.format(new Date());

        // ✅ ENCODAGE de la valeur avant création du cookie
        String dateEncodee = URLEncoder.encode(
                dateActuelle,
                StandardCharsets.UTF_8
        );

        Cookie cookieLastVisit = new Cookie("lastVisit", dateEncodee);//cookieLastVisit = Ce qu'on met dans le cookie (t) → On sauvegarde pour plus tard
        cookieLastVisit.setMaxAge(24 * 60 * 60); // 24 heures
        cookieLastVisit.setPath("/");
        response.addCookie(cookieLastVisit);

        // 3. TRANSMISSION de la date décodée (lisible) à la JSP
        request.setAttribute("derniereVisite", derniereVisite);


        // === PARTIE 5 : RÉCUPÉRATION DEPUIS LA BASE DE DONNÉES ===
        // ✅ Appel via l'INTERFACE (découplage total)
        List<Produit> listeProduits = stockService.recupererTousLesProduits();

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