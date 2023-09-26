<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>
<style>
.card{
    border-radius: 4px;
    box-shadow: 0 6px 10px rgba(0,0,0,.08), 0 0 6px rgba(0,0,0,.05);
      transition: .3s transform cubic-bezier(.155,1.105,.295,1.12),.3s box-shadow,.3s -webkit-transform cubic-bezier(.155,1.105,.295,1.12);
}

.card:hover{
     transform: scale(1.05);
  box-shadow: 5px 5px 5px #3cf281;
}
</style>
<main class="row col-12 mb-5"
	style=" min-height: 500px;">
	<div class="col"></div>
	<div class="row text-success text-center mt-4">
		<h1>Liste des Enchères</h1>
	</div>
	<div class="col-8 offset-4 ">
	
		<div class=" col-6">

			<form class="d-flex " method="GET" action="">
				<input class="form-control me-2" type="search" name="q"
					placeholder="Recherche..." aria-label="Search">
				<button class="btn btn-outline-success" type="submit">
					<i class="fa-solid fa-magnifying-glass"></i>
				</button>
			</form>
		</div>
		<div class="mb-3 d-flex .flex-lg-row mt-2 col-3 ">
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
		<div class="row mt-5 mb-5">
		<c:if test="${ utilisateur != null }">
			<form action="" method="GET">
  		<div class="col-6">
  			<div class="container-fluid">
  				<div class="row">
  					<div class="col-6">
  						<div class="form-check">
						  <input class="form-check-input" checked="true" type="radio" value="achats" onchange="change(event)" name="type" id="achats">
						  <label class="form-check-label" for="achats" >
						    Achats
						  </label>
						</div>
						<div class="container ck-achats">
							<div class="form-check">
							  <input class="form-check-input" name="ec" type="checkbox" value="" id="eo">
							  <label class="form-check-label" for="eo">
							    Enchères ouvertes
							  </label>
							</div>
							<div class="form-check">
							  <input class="form-check-input" type="checkbox" value="" id="meec">
							  <label class="form-check-label" for="meec">
							    Mes enchères en cours
							  </label>
							</div>
							<div class="form-check">
							  <input class="form-check-input" type="checkbox" value="" id="mer">
							  <label class="form-check-label" for="mer">
							    Mes enchères remportées
							  </label>
							</div>							
						</div>						
  					</div>
  					<div class="col-6">
  						<div class="form-check">
						  <input class="form-check-input" type="radio" name="type" value="mes vente" id="mes_ventes" onchange="change(event)">
						  <label class="form-check-label" for="mes_ventes">
						    Mes ventes
						  </label>
						</div> 
						<div class="container ck-ventes">
							<div class="form-check">
							  <input name="mvec" class="form-check-input" disabled type="checkbox"  id="mvec">
							  <label class="form-check-label" for="mvec">
							    Mes vente en cours
							  </label>
							</div>
							<div class="form-check">
							  <input class="form-check-input" disabled type="checkbox" value="" id="vnd">
							  <label class="form-check-label" for="vnd">
							    ventes non débutées
							  </label>
							</div>
							<div class="form-check">
							  <input class="form-check-input" disabled type="checkbox" value="" id="vt">
							  <label class="form-check-label" for="vt">
							    ventes terminées
							  </label>
							</div>							
						</div>						 					
  					</div>
  				</div>
  			</div>
  		</div>
  		<button type="submit" class="btn btn-primary mt-2">Filtrer</button>
  		</form>
  		</c:if>
  	</div>
		
	</div>
	<div class="col-10 offset-1 flex-wrap d-flex justify-content-around ">
	
		<c:forEach var="article" items="${ articles }">
			<div class="card col-3 m-3 border-success  ">
				<div class="card-header">
					<a
						href="${ pageContext.request.contextPath }/vente/details?id=${ article.getNoArticle() }">
						${ article.getNomArticle() }</a>
				</div>
				<div class="card-body">
					<h5 class="card-title">Prix: ${ article.getPrixInitial() }
						points</h5>
					<p class="card-text">Fin de l'enchere: ${ article.getDateFinEncheres() }</p>
					<p class="card-text">
						vendeur : <a
							href="${ pageContext.request.contextPath }/profil-vendeur?id=${ article.getVendeur().getNoUtilisateur() }"
							class="card-link"> ${ article.getVendeur().getPseudo() }</a>
					</p>
				</div>
			</div>
		</c:forEach>


	</div>
</main>
	<script src="${ pageContext.request.contextPath }/assets/js/main.js" ></script>
 
<%@ include file="/WEB-INF/fragments/footer.jspf"%>