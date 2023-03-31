<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajout d'un genre</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
	            <h1 class="card-title">Ajout d'un genre</h1>
	            <c:if test="${not empty error}">
	                <div class="alert alert-danger">${error}</div>
	            </c:if>
	            <form method="POST" action="${pageContext.request.contextPath}/genre">
	                <div class="form-group">
	                    <label for="genreName">Nom:</label>
	                    <input type="text" class="form-control" id="genreName" name="genreName" required>
	                </div>
	                <button type="submit" class="btn btn-primary">Ajouter</button>
	            </form>
	        </div>
	    </div>
	</div>
</body>
</html>
