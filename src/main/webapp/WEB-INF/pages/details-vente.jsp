<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>
<main class="row">
	<div class="col">
		<div class="row text-center mt-4">
			<h1>details de la vente</h1>
		</div>
		<div class="row mt-5">
			<div class="col-4 offset-4 mb-4">

				
					<div class="mb-1">
						<label for="nom_article" class="form-label">Article:</label> <input
							type="text" value="${article.getNomArticle()}"
							readonly="readonly" class="form-control" name="nom_article"
							id="nom_article">
					</div>
					<div class="mb-1">
						<label for="description" class="form-label">Description:</label> <input
							type="text" class="form-control" readonly="readonly"
							name="description" value="${article.getDescription()}"
							id="description">
					</div>
					<div class="mb-1">
						<label for="categories" class="form-label">Catégorie:</label> <input
							type="text" value="${article.getCategorie().getLibelle()}"
							readonly="readonly" class="form-control" name="prix_initial"
							id="prix_initial">

					</div>
					<div class="mb-1">
						<label for="image" class="form-label">Photo de l'article:</label>
						<!-- <input type="file" readonly="readonly" name="image"
							accept="image/png, image/gif, image/jpeg" /> -->
					</div>
					<div class="mb-3">
						<label for="montant_enchere" class="form-label">Meilleur offre:</label>
						<input type="number" value="${enchere.getMontantEnchere()}"
							readonly="readonly" class="form-control" name="montant_enchere"
							id="montant_enchere">
					</div>
					<div class="mb-3">
						<label for="prix_initial" class="form-label">Mise à prix:</label>
						<input type="number" value="${article.getPrixInitial()}"
							readonly="readonly" class="form-control" name="prix_initial"
							id="prix_initial">
					</div>
				
					<div class="mb-1">
						<label for="date_fin_encheres" class="form-label">Fin de
							l'enchères</label> <input type="date"
							value="${article.getDateFinEncheres()}" readonly="readonly"
							class="form-control" name="date_fin_encheres"
							id="date_fin_encheres">
					</div>
					<div class="mb-3">
						<fieldset style="  border: 1px solid #32fbe2; padding:15px; border-radius:10px;">
							<legend class="float-none w-auto p-2">Retrait</legend>
							<label for="rue" class="form-label">Rue</label> <input
								type="text" readonly="readonly"
								value="${article.getVendeur().getRue()}" class="form-control mb-2"
								name="rue" id="rue"> <label for="code_postal"
								class="form-label">Code Postal</label> <input type="text"
								readonly="readonly"
								value="${article.getVendeur().getCodePostal()}"
								class="form-control mb-2" name="code_postal" id="code_postal">
							<label for="ville" class="form-label">Ville</label> <input
								type="text" readonly="readonly"
								value="${article.getVendeur().getVille()}" class="form-control mb-2"
								name="ville" id="ville">
						</fieldset>
					</div>
					<div class="mb-3">
						<label for="vendeur" class="form-label">Vendeur:</label>
						<input type="text" value="${article.getVendeur().getPseudo()}"
							readonly="readonly" class="form-control" name="prix_initial"
							id="prix_initial">
					</div>
					<form action="<%=request.getContextPath()+"/encherir"%>" method="POST">
					<div class="mb-3">
					<label for="montantEnchere" class="form-label">Ma proposition:</label>
					<input type="number" class="form-control" name="montantEnchere"
							id="prix_initial">	
					</div>
					<button class="btn btn-primary offset-5" type="submit">
							Enchérir
						</button>
					</form>
			</div>
		</div>
	</div>

</main>
<%@ include file="/WEB-INF/fragments/footer.jspf"%>