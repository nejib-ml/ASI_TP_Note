<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Connexion</title>
<!-- Ajouter les liens vers les fichiers CSS et JS de Bootstrap -->
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
	<div class="center-card">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-6">
					<div class="card mt-4">
						<div class="card-header">
							<h1 class="text-center">Connexion</h1>
						</div>
						<div class="card-body">
							<c:if test="${not empty error}">
								<div class="alert alert-danger">${error}</div>
							</c:if>
							<form method="POST"
								action="${pageContext.request.contextPath}/login">
								<div class="form-group">
									<label for="mail">Mail:</label> <input type="email"
										class="form-control" id="mail" name="mail" required>
								</div>
								<div class="form-group">
									<label for="password">Mot de passe:</label> <input
										type="password" class="form-control" id="password"
										name="password" required>
								</div>
								<input type="submit" class="btn btn-primary" value="Connecter">
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
