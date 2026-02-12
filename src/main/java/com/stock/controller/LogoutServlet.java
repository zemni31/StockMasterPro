package com.stock.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet de déconnexion
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/deconnexion"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer la session (sans en créer une nouvelle)
        HttpSession session = request.getSession(false);

        if (session != null) {
            // Détruire la session
            session.invalidate();
        }

        // Redirection vers login
        response.sendRedirect("login.jsp");
    }
}
