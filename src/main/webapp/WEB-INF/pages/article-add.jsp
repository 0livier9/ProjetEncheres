<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>
<main class="row">
	<div class="col">
		<div class="row text-center mt-4">
			<h1>Ajouter un Article</h1>
		</div>
		<div class="row mt-5">
			<div class="col-4 offset-4">
				<form action="" method="POST">
					<div class="mb-3">
						<label for="nom_article" class="form-label">Article:</label> <input
							type="text" class="form-control" name="nom_article"
							id="nom_article">
					</div>
					<div class="mb-3">
						<label for="description" class="form-label">Description:</label> <input
							type="text" class="form-control" name="description"
							id="description">
					</div>
					<div class="mb-3">
						<label for="categories" class="form-label">Catégorie:</label> <select
							class="form-control" name="categories" id="categories">
							<c:forEach var="categorie" items="${categories}">
								<option value="${categorie.noCategorie}">${categorie.libelle}</option>
							</c:forEach>
						</select>
					</div>
					<div class="mb-3">
						<label for="image" class="form-label">Photo de l'article:</label>
						<input type="file" name="image"
							accept="image/png, image/gif, image/jpeg" />
					</div>
					<div class="mb-3">
						<label for="prix_initial" class="form-label">Mise à prix:</label>
						<input type="number" class="form-control" name="prix_initial"
							id="prix_initial">
					</div>
					<div class="mb-3">
						<label for="date_debut_encheres" class="form-label">Début
							de l'enchères</label> <input type="date" class="form-control"
							name="date_debut_encheres" id="date_debut_encheres">
					</div>
					<div class="mb-3">
						<label for="date_fin_encheres" class="form-label">Fin de
							l'enchères</label> <input type="date" class="form-control"
							name="date_fin_encheres" id="date_fin_encheres">
					</div>
					<div class="mb-3">
						<fieldset>
							<legend>Retrait</legend>
							<label for="rue" class="form-label">rue</label> <input
								type="text" value="${Rue}" class="form-control" name="rue" id="rue"> <label
								for="code_postal" class="form-label">Code Postal</label> <input
								type="text" value="${codePostal}"
								  class="form-control" name="code_postal"
								id="code_postal"> <label for="ville" class="form-label">Ville</label>
							<input type="text" value="${ville}" class="form-control" name="ville" id="ville">
						</fieldset>
					</div>
					<div class="mb-3 text-center mt-5">
						<button class="btn btn-primary" type="submit">
							<i class="fa-regular fa-floppy-disk"></i>
						</button>
						<button class="btn btn-basic" type="reset">
							<i class="fa-solid fa-eraser"></i>
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf"%>