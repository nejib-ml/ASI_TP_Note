<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="home">Ma bibliothèque</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNav" aria-controls="navbarNav"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="home">Livres</a></li>
			<c:if test="${not empty user}">
				<li class="nav-item"><a class="nav-link" href="genre">Genres</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="author">Auteurs</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="borrow">Mes
						emprunts</a></li>
			</c:if>

			<c:if test="${not empty user}">
				<c:if test="${user.admin}">
					<li class="nav-item"><a class="nav-link" href="returnBook">Rendre
							un livre</a></li>
				</c:if>
			</c:if>

		</ul>
		<c:if test="${not empty user}">
			<ul class="navbar-nav ml-auto">
				<c:if test="${not empty user}">
					<c:if test="${user.admin}">
						<li class="nav-item"><a class="nav-link" href="register">Utilisateurs</a>
						</li>
					</c:if>
				</c:if>
				<li class="nav-item"><a class="nav-link" href="logout">Déconnecter</a>
				</li>
			</ul>
		</c:if>
	</div>
</nav>
