<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- Ajouter la taglib form de spring -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- Ajouter la lib core de jstl -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajout</title>
<!-- Lier le fichier css de bootstrap à ma page -->
<link rel="stylesheet" href="<c:url value='/assets/css/bootstrap.css'/> " />
</head>
<body>
	<%@ include file="../../template/header.html"%>
	<h1 style="color: red; text-align: center;">Formulaire d'ajout</h1>

	<form:form method="POST" action="submitAdd" modelAttribute="eAdd">
		Nom: <form:input path="nom" />
		<br />
		Prenom: <form:input path="prenom" />
		<br />
		Date : <form:input type="date" path="dn" />
		<br />
		<input type="submit" value="Ajouter"/>
	</form:form>


</body>
</html>