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
						<label for="categorie" class="form-label">Catégorie:</label> <select
							class="form-control" name="categorie" id="categorie">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
						</select>
					</div>
					<div class="mb-3">
						<label for="myImage" class="form-label">Photo de l'article:</label>
						<input type="file" name="myImage"
							accept="image/png, image/gif, image/jpeg" /> 
					</div>
					<div class="mb-3">
						<label for="prix_initial" class="form-label">Mise à prix:</label>
						<input type="number" class="form-control" name="prix_initial"
							id="prix_initial">
					</div>
					<div class="mb-3">
						<label for="date_debut_encheres" class="form-label">Début de l'enchères</label> <input
							type="date" class="form-control" name="date_debut_encheres" id="date_debut_encheres">
					</div>
					<div class="mb-3">
						<label for="date_fin_encheres" class="form-label">Fin de l'enchères</label> <input
							type="date" class="form-control" name="date_fin_encheres" id="date_fin_encheres">
					</div>
					<div class="mb-3">
						<label for="Retrait" class="form-label">Retrait</label> <input
							type="text" class="form-control" name="Retrait" id="Retrait">
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