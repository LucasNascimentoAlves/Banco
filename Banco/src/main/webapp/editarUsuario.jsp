<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<form action="AtualizarUsuarioServlet" method="post">
    <input type="hidden" name="id" value="${usuario.id}">

    <label for="nome">Nome:</label>
    <input type="text" name="nome" value="${usuario.nome}" required><br>

    <label for="email">Email:</label>
    <input type="email" name="email" value="${usuario.email}" required><br>

    <input type="submit" value="Atualizar">
</form>

</body>
</html>