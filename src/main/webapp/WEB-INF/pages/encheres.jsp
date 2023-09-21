<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>
<main class="row">
	<div class="col">
		<div class="row text-center mt-4">
			<h1>Liste des Enchères</h1>
		</div>
		<div class="row mt-5">
			<div class="col-8 offset-2">

				<c:forEach var="article" items="${ articles }">


					<div class=col-4>
						<p>${ article.getNomArticle() }</p>
						<p>${ article.getPrixInitial() }</p>
						<p>${ article.getDateFinEncheres() }</p>
						<p>${ article.getVendeur().getPseudo() }</p>
						<p>Test Nom: ${article.getNomArticle()}</p>
						<p>Test Prix: ${article.getPrixInitial()}</p>
						<p>Test Date: ${article.getDateFinEncheres()}</p>
						<p>Test Vendeur: ${article.getVendeur().getPseudo()}</p>


						<a class="btn btn-dark" href="/encheres/détails"> <i
							class="fa-solid fa-eye"></i>
						</a>

					</div>
				</c:forEach>


			</div>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf"%>