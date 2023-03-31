<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des utilisateurs</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
	<header>
		<%@ include file="navBar.jsp"%>
	</header>
	<div class="container">
		<h1 class="my-4">Liste des utilisateurs</h1>
		<c:if test="${not empty error}">
			<div class="alert alert-danger">${error}</div>
		</c:if>

		<c:if test="${not empty user}">
			<c:if test="${user.admin}">
				<form action="register.jsp" method="get">
					<input type="submit" value="Ajouter un utilisateur"
						class="btn btn-primary">
				</form>
				<br>
			</c:if>
		</c:if>

		<c:if test="${not empty users}">
			<table class="table table-striped">
				<thead class="thead-dark">
					<tr>
						<th>Prénom</th>
						<th>Nom</th>
						<th>Mail</th>
						<th>Rôle</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="user">
						<tr>
							<td>${user.firstname}</td>
							<td>${user.lastname}</td>
							<td>${user.mail}</td>
							<td><c:choose>
									<c:when test="${user.admin}">Administrateur</c:when>
									<c:otherwise>Utilisateur simple</c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>
