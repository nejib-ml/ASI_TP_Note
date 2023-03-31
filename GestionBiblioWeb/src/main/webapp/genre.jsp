<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des genres</title>
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
		<h1 class="my-4">Liste des genres des livres</h1>
		<c:if test="${not empty error}">
			<div class="alert alert-danger">${error}</div>
		</c:if>

		<c:if test="${not empty user}">
			<c:if test="${user.admin}">
				<form action="addGenre.jsp" method="get">
					<input type="submit" class="btn btn-primary"
						value="Ajouter un genre">
				</form>
			</c:if>
		</c:if>

		<c:if test="${not empty genres}">
			<table class="table table-striped mt-4">
				<thead class="thead-dark">
					<tr>
						<th>Nom</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${genres}" var="genre">
						<tr>
							<td>${genre.name}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>
