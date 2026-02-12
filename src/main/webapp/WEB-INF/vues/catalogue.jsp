<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Catalogue - StockMaster Pro</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 20px;
        }

        .container {
            max-width: 1000px;
            margin: 0 auto;
            background: white;
            border-radius: 15px;
            box-shadow: 0 10px 40px rgba(0,0,0,0.2);
            overflow: hidden;
        }

        header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 30px;
            text-align: center;
        }

        header h1 {
            font-size: 2.5em;
            margin-bottom: 10px;
        }

        header p {
            font-size: 1.1em;
            opacity: 0.9;
        }

        .content {
            padding: 30px;
        }

        .info-box {
            background: #f8f9fa;
            border-left: 4px solid #667eea;
            padding: 15px;
            margin-bottom: 25px;
            border-radius: 5px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }

        thead {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }

        th {
            padding: 15px;
            text-align: left;
            font-weight: 600;
            text-transform: uppercase;
            font-size: 0.9em;
            letter-spacing: 0.5px;
        }

        td {
            padding: 15px;
            border-bottom: 1px solid #e0e0e0;
        }

        tbody tr {
            transition: background-color 0.3s;
        }

        tbody tr:hover {
            background-color: #f5f5f5;
        }

        .price {
            color: #28a745;
            font-weight: bold;
            font-size: 1.1em;
        }

        .badge {
            display: inline-block;
            padding: 5px 10px;
            background: #667eea;
            color: white;
            border-radius: 20px;
            font-size: 0.85em;
            font-weight: 600;
        }

        .empty-message {
            text-align: center;
            padding: 40px;
            color: #666;
            font-size: 1.2em;
        }
        .user-info {
            background: #e8f5e9;
            border-left: 4px solid #4caf50;
            padding: 15px;
            margin-bottom: 25px;
            border-radius: 5px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .btn-logout {
            background: #f44336;
            color: white;
            padding: 8px 15px;
            border-radius: 5px;
            text-decoration: none;
            font-size: 0.9em;
            transition: background 0.3s;
        }

        .btn-logout:hover {
            background: #d32f2f;
        }
    </style>
</head>
<body>
<div class="container">
    <header>
        <h1>📦Bienvenue dans StockMaster Pro</h1>
        <p>Système de Gestion de Stock - Catalogue Produits</p>
    </header>
    <div class="content">
        <!-- NOUVEAU : Affichage utilisateur connecté -->
        <c:if test="${not empty sessionScope.user}">
        <div class="user-info">
            👤 Utilisateur connecté : <strong>${sessionScope.user}</strong>
            <a href="deconnexion" class="btn-logout">Se déconnecter</a>
        </div>
        </c:if>

        <!-- Reste du contenu existant -->
        <div class="info-box">

    <div class="content">


        <h2>Liste des Produits
            <span class="badge">${listeProduits.size()} produit(s)</span>
        </h2>

        <%-- Vérification si la liste existe et n'est pas vide --%>
        <c:choose>
            <c:when test="${not empty listeProduits}">
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom du Produit</th>
                        <th>Prix (€)</th>
                    </tr>
                    </thead>
                    <tbody>
                        <%-- Boucle JSTL pour parcourir la liste --%>
                    <c:forEach var="produit" items="${listeProduits}">
                        <tr>
                            <td>#${produit.id}</td>
                            <td>${produit.nom}</td>
                            <td class="price">
                                <fmt:formatNumber value="${produit.prix}"
                                                  type="currency"
                                                  currencySymbol="€"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="empty-message">
                    ❌ Aucun produit disponible dans le catalogue
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>