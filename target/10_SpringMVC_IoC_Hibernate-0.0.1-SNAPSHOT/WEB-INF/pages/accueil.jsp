<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- Ajouter la lib core de jstl -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Ajouter la lib fmt de jstl qui sert à l'internationalisation de mon application  -->
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Accueil</title>

<!-- Lier le fichier css de bootstrap à ma page -->
<link rel="stylesheet"
	href="<c:url value='/assets/css/bootstrap.css'/> " />
</head>
<body>
	<%@ include file="../../template/header.html"%>

	<table class="table table-bordered">
		<tr>
			<th>ID</th>
			<th>Nom</th>
			<th>Prenom</th>
			<th>Date</th>
			<th>Operations</th>
		</tr>

		<c:forEach var="e" items="${etudiants}">
			<tr>
				<td>${e.id}</td>
				<td>${e.nom}</td>
				<td>${e.prenom}</td>
				<td><fmt:formatDate value="${e.dn}" pattern="dd-MM-yyyy"/></td>
				<td>
					<a href="<c:url value='/ecole/submitDelete?pId=${e.id}' />">Supprimer</a> | 
					<a href="<c:url value='/ecole/linkUpdate?pId=${e.id}' />">Modifier</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>