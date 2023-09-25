<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>
<main class="row col-12"
	style="background-color: black; min-height: 500px;">
	<div class="col"></div>
	<div class="row text-center mt-4">
		<h1>Liste des Enchères</h1>
	</div>
	<div class="col-8 offset-4">
		<div class=" col-6">

			<form class="d-flex " method="GET" action="">
				<input class="form-control me-2" type="search" name="q"
					placeholder="Recherche..." aria-label="Search">
				<button class="btn btn-outline-success" type="submit">
					<i class="fa-solid fa-magnifying-glass"></i>
				</button>
			</form>
		</div>
		<div class="mb-3 col-3 ofsset-1">
			<form action="" method="GET">
				<label for="categories" class="form-label">Catégories:</label> <select
					class="form-control" name="categories" id="categories">
					<option>Selectionner une categorie</option>
					<c:forEach var="categorie" items="${categories}">
						<option value="${categorie.noCategorie}">${categorie.libelle}</option>
					</c:forEach>
				</select>
				<button type="submit" class="btn btn-primary mt-2">Filtrer</button>
			</form>
		</div>
	</div>
	<div class="col-10 offset-1 flex-wrap d-flex justify-content-around ">
		<c:forEach var="article" items="${ articles }">
			<div class="card col-3 m-2 border-primary  ">
				<div class="card-header">
					<a
						href="${ pageContext.request.contextPath }/vente/details?id=${ article.getNoArticle() }">
						${ article.getNomArticle() }</a>
				</div>
				<div class="card-body">
					<h4 class="card-title">Prix: ${ article.getPrixInitial() }
						points</h4>
					<p class="card-text">Fin de l'enchere: ${ article.getDateFinEncheres() }</p>
					<p class="card-text">
						vendeur : <a href="${ pageContext.request.contextPath }/profil-vendeur?id=${ article.getVendeur().getNoUtilisateur() }" class="card-link"> ${ article.getVendeur().getPseudo() }</a>
					</p>
				</div>
			</div>
		</c:forEach>


	</div>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf"%>