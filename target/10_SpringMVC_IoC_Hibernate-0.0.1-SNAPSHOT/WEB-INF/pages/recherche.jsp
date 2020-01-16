<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- Ajouter la lib core de jstl -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recherche</title>

<!-- Lier le fichier css de bootstrap à ma page -->
<link rel="stylesheet" href="<c:url value='/assets/css/bootstrap.css'/> " />
</head>
<body>
	<%@ include file="../../template/header.html"%>
	<h1 style="color: red; text-align: center;">Formulaire de la
		suppression</h1>

	<form method="get" action="submitGet">
		ID: <input type="number" name="pId" /> <br /> <input type="submit"
			value="Rechercher" />
	</form>


	<c:if test="${not empty eGet}">
		<h1 style="color: red; text-align: center;">L'etudiant Recherché:
		</h1>
		<table class="table table-bordered">
			<tr>
				<th>ID</th>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Date</th>
			</tr>


			<tr>
				<td>${eGet.id}</td>
				<td>${eGet.nom}</td>
				<td>${eGet.prenom}</td>
				<td>${eGet.dn}</td>
			</tr>

		</table>
	</c:if>
	<h1 style="color: red; text-align: center;">${msg}</h1>
</body>
</html>