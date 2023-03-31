<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Emprunts en cours</title>
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
		<h1 class="my-4">Emprunts en cours</h1>
		<c:if test="${empty loansDueDates}">
			<div class="alert alert-danger">Vous n'avez aucun livre
				emprunté pour le moment.</div>
		</c:if>
		<c:if test="${not empty error}">
			<div class="alert alert-danger">${error}</div>
		</c:if>
		<c:if test="${not empty loansDueDates}">
			<table class="table table-striped">
				<thead class="thead-dark">
					<tr>
						<th>Titre</th>
						<th>Auteur</th>
						<th>Date d'emprunt</th>
						<th>Date limite de retour</th>
						<th>Statut</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${loansDueDates}" var="loanDueDate">
						<tr>
							<td>${loanDueDate.key.book.title}</td>
							<td>${loanDueDate.key.book.author.name}</td>
							<td><fmt:formatDate value="${loanDueDate.key.startDate}"
									pattern="dd/MM/yyyy" /></td>
							<td><fmt:formatDate value="${loanDueDate.key.endDate}"
									pattern="dd/MM/yyyy" /></td>
							<td><c:choose>
									<c:when test="${loanDueDate.value}">Bientôt dû</c:when>
									<c:otherwise>En cours</c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>
