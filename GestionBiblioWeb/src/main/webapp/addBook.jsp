<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajouter un livre</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
.center-card {
	display: flex;
	justify-content: center;
	align-items: center;
	margin-top: 5%;
}
</style>
</head>
<body>
	<header>
		<%@ include file="navBar.jsp"%>
	</header>
	<div class="center-card">
		<div class="card" style="width: 25rem;">
			<div class="card-body">
				<h1 class="card-title">Ajouter un nouveau livre</h1>
				<form action="addBook" method="post">
					<div class="form-group">
						<label for="title">Titre :</label> <input type="text" id="title"
							name="title" class="form-control" required>
					</div>
					<div class="form-group">
						<label for="authorId">Auteur :</label> <select id="authorId"
							name="authorId" class="form-control">
							<c:forEach items="${authors}" var="author">
								<option value="${author.id}">${author.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="genreId">Genre :</label> <select id="genreId"
							name="genreId" class="form-control">
							<c:forEach items="${genres}" var="genre">
								<option value="${genre.id}">${genre.name}</option>
							</c:forEach>
						</select>
					</div>
					<input type="submit" value="Ajouter" class="btn btn-primary">
				</form>
			</div>
		</div>
	</div>
</body>
</html>
