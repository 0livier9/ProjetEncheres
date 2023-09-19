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

				<c:forEach var="enchere" items="${ encheres }">
					
			
			<div>
						<p>${ enchere.user.getPseudo() }</p>
						<p>${ enchere.article.getNomArticle() }</p>
						<p>${ enchere.dateEnchere }</p>
						<p>${ enchere.montantEnchere }</p>
						<p>${ enchere.user.getVille() }</p>

					
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