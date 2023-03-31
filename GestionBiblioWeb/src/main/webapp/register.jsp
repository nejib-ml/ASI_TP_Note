<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Création d'un utilisateur</title>
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
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card mt-4">
					<div class="card-header">
						<h1 class="text-center">Création d'un utilisateur</h1>
					</div>
					<div class="card-body">
						<c:if test="${not empty error}">
							<div class="alert alert-danger">${error}</div>
						</c:if>
						<form method="POST"
							action="${pageContext.request.contextPath}/register">
							<div class="form-group">
								<label for="firstname">Prénom:</label> <input type="text"
									class="form-control" id="firstname" name="firstname" required>
							</div>

							<div class="form-group">
								<label for="lastname">Nom:</label> <input type="text"
									class="form-control" id="lastname" name="lastname" required>
							</div>

							<div class="form-group">
								<label for="mail">Mail:</label> <input type="email"
									class="form-control" id="mail" name="mail" required>
							</div>

							<div class="form-group">
								<label for="password">Mot de passe:</label> <input
									type="password" class="form-control" id="password"
									name="password" required>
							</div>

							<div class="form-group">
								<label for="role">Rôle :</label> <select class="form-control"
									id="role" name="role">
									<option selected="selected" value="0">Utilisateur
										simple</option>
									<option value="1">Administrateur</option>
								</select>
							</div>

							<input type="submit" class="btn btn-primary"
								value="Créer un compte">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
