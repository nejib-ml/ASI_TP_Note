<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Ma bibliothèque</title>
<!-- Ajouter les liens vers les fichiers CSS et JS de Bootstrap -->
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
		<h1 class="mt-4">Catalogue des livres</h1>

		<c:if test="${not empty dueSoon}">
			<div class="alert alert-danger">Attention! Vous avez des livres
				à rendre bientôt</div>
		</c:if>
		<br>
		<c:if test="${not empty user}">
			<c:if test="${user.admin}">
				<form action="addBook" method="get">
					<input type="submit" class="btn btn-primary"
						value="Ajouter un livre">
				</form>
			</c:if>
		</c:if>
		<br>
		<!-- Formulaire de recherche -->
		<form class="form-inline my-3" action="" method="post">
			<label class="" for="authorName">Auteur :</label> <input
				class="form-control mr-2" type="text" id="authorName"
				name="authorName" value="${authorName}"> <label class=""
				for="title">Titre:</label> <input class="form-control mr-2"
				type="text" id="title" name="title" value="${title}"> <label
				class="" for="genre">Genre :</label> <select
				class="form-control mr-2" id="genreId" name="genreId">
				<option value="-1">Tous les genres</option>
				<c:forEach items="${genres}" var="genre">
					<option value="${genre.id}">${genre.name}</option>
				</c:forEach>
			</select> <label class="" for="availableOnly">Disponibles seulement </label> <input
				class="form-check-input mr-2" type="checkbox" id="availableOnly"
				name="availableOnly" ${availableOnly ? 'checked' : ''}> <input
				class="btn btn-primary" type="submit" value="Rechercher">
		</form>

		<!-- Liste des livres -->
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Titre</th>
					<th>Auteur</th>
					<th>Genre</th>
					<th>Disponible</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${books}" var="book">
					<tr>
						<td>${book.title}</td>
						<td>${book.author.name}</td>
						<td>${book.genre.name}</td>
						<td>${book.available ? 'Oui' : 'Non'}</td>
						<td><c:if test="${book.available}">
								<form class="form-inline" action="borrow" method="post">
									<input type="hidden" name="bookId" value="${book.id}">
									<input class="btn btn-primary" type="submit" value="Emprunter">
								</form>
							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
