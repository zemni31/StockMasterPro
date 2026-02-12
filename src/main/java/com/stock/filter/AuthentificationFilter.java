package com.stock.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/catalogue")
public class AuthentificationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        // Vérifier si l'utilisateur est connecté
        if (session != null && session.getAttribute("user") != null) {
            // ✅ Utilisateur connecté → LAISSER PASSER
            chain.doFilter(request, response);
        } else {
            // ❌ Utilisateur non connecté → BLOQUER et rediriger
            httpRequest.setAttribute("error", "Vous devez vous connecter pour accéder au catalogue.");
            httpRequest.getRequestDispatcher("login.jsp").forward(httpRequest, httpResponse);
        }
    }
}
