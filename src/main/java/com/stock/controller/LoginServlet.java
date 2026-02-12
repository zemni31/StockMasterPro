package com.stock.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet de traitement de la connexion
 * Pattern MVC : Contrôleur pour l'authentification
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/connexion"})
public class LoginServlet extends HttpServlet {

    // Identifiants en dur (pour simulation)
    private static final String VALID_LOGIN = "admin";
    private static final String VALID_PASSWORD = "123";

    /**
     * Affiche le formulaire de connexion (si accès en GET)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirection vers la page de login
        response.sendRedirect("login.jsp");
    }

    /**
     * Traite la soumission du formulaire de connexion
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // === RÉCUPÉRATION DES PARAMÈTRES DU FORMULAIRE ===
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        // === VALIDATION DES IDENTIFIANTS ===
        if (VALID_LOGIN.equals(login) && VALID_PASSWORD.equals(password)) {

            // ✅ AUTHENTIFICATION RÉUSSIE

            // 1. Récupérer ou créer une session
            HttpSession session = request.getSession(true);

            // 2. Stocker le nom d'utilisateur dans la session
            session.setAttribute("user", login);

            // 3. (Optionnel) Informations supplémentaires
            session.setAttribute("loginTime", System.currentTimeMillis());

            // 4. Message de succès (optionnel)
            System.out.println("✅ Connexion réussie pour : " + login);

            // 5. Redirection vers le catalogue
            response.sendRedirect("catalogue");

        } else {

            // ❌ AUTHENTIFICATION ÉCHOUÉE

            System.out.println("❌ Échec de connexion pour : " + login);

            // Ajouter message d'erreur et forward
            request.setAttribute("erreurLogin", "Identifiants incorrects ! Veuillez réessayer.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet de gestion de l'authentification";
    }
}