<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>
<main class="row" style="background-color: black;">
	<div class="col">
		<div class="row text-center mt-4">
			<h1>Liste des Enchères</h1>
		</div>


			<c:forEach var="article" items="${ articles }">
				<div class="card border-primary mb-3" >
					<div class="card-header">${ article.getNomArticle() }</div>
					<div class="card-body">
						<h4 class="card-title">${ article.getPrixInitial() }</h4>
						<p class="card-text">${ article.getDateFinEncheres() }</p>
						<p class="card-text">	vendeur : <a href="#" class="card-link"> ${ article.getVendeur().getPseudo() }</a></p>
					</div>
				</div>

			<%-- 	<div class="card border-dark mb-3" style="min-width: 10vm;">
					<div class="card-body">
						<h5 class="card-title"></h5>
						<h6 class="card-text mb-2 text-muted"></h6>
						<p class="card-text"></p>
						<p class="card-text">
							vendeur : <a href="#" class="card-link"> ${ article.getVendeur().getPseudo() }</a>
						</p>


						<a class="btn btn-dark" href="/encheres/détails"> Détails </a>
					</div>
				</div> --%>

			</c:forEach>


		</div>
	</div>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf"%>