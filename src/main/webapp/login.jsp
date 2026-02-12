<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion - StockMaster Pro</title>
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
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }

        .login-container {
            background: white;
            border-radius: 15px;
            box-shadow: 0 10px 40px rgba(0,0,0,0.3);
            width: 100%;
            max-width: 400px;
            overflow: hidden;
        }

        .login-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 30px;
            text-align: center;
        }

        .login-header h1 {
            font-size: 2em;
            margin-bottom: 10px;
        }

        .login-header p {
            opacity: 0.9;
        }

        .login-body {
            padding: 40px 30px;
        }

        .form-group {
            margin-bottom: 25px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: #333;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 12px 15px;
            border: 2px solid #e0e0e0;
            border-radius: 8px;
            font-size: 1em;
            transition: border-color 0.3s;
        }

        input[type="text"]:focus,
        input[type="password"]:focus {
            outline: none;
            border-color: #667eea;
        }

        .btn-login {
            width: 100%;
            padding: 14px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 1.1em;
            font-weight: 600;
            cursor: pointer;
            transition: transform 0.2s;
        }

        .btn-login:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }

        .error-message {
            background: #fee;
            border-left: 4px solid #f44336;
            color: #c33;
            padding: 12px 15px;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        .info-box {
            background: #e3f2fd;
            border-left: 4px solid #2196F3;
            padding: 12px 15px;
            border-radius: 5px;
            margin-bottom: 20px;
            font-size: 0.9em;
        }
    </style>
</head>
<body>
<div class="login-container">
    <div class="login-header">
        <h1>🔐 StockMaster Pro</h1>
        <p>Connexion au système</p>
    </div>

    <div class="login-body">
        <!-- Message d'erreur du LoginServlet -->
        <c:if test="${not empty erreurLogin}">
            <div class="error-message">
                ❌ ${erreurLogin}
            </div>
        </c:if>

        <!-- Message d'erreur du filtre d'authentification -->
        <c:if test="${not empty error}">
            <div class="error-message">
                ❌ ${error}
            </div>
        </c:if>



        <!-- Formulaire de connexion -->
        <form action="connexion" method="POST">
            <div class="form-group">
                <label for="login">Identifiant</label>
                <input type="text"
                       id="login"
                       name="login"
                       placeholder="Entrez votre login"
                       required
                       autofocus>
            </div>

            <div class="form-group">
                <label for="password">Mot de passe</label>
                <input type="password"
                       id="password"
                       name="password"
                       placeholder="Entrez votre mot de passe"
                       required>
            </div>

            <button type="submit" class="btn-login">
                Se connecter
            </button>
        </form>
    </div>
</div>
</body>
</html>