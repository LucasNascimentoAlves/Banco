<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<form action="LoginServlet" method="post">
    <label for="login">Login:</label>
    <input type="text" name="login" id="login" required><br>

    <label for="senha">Senha:</label>
    <input type="password" name="senha" id="senha" required><br>

    <input type="submit" value="Entrar">
</form>
</body>
</html>