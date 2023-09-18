<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ include file="/WEB-INF/fragments/header.jspf" %>
<main class="row">
	<div class="col">
		<div class="row text-center mt-4">
			<h1> Ajouter un jeu </h1>
		</div>
		<div class="row mt-5">
			<div class="col-4 offset-4">
			    <form action="" method="POST" >					    
					<div class="mb-3">
					  <label for="nom_article" class="form-label">Article:</label>
					  <input type="text" class="form-control" name="nom_article" id="nom_article" >
					</div>
					<div class="mb-3">
					  <label for="description" class="form-label">Description:</label>
					  <input type="text" class="form-control" name="description"  id="description" >
					</div>
					<div class="mb-3">
					  <label for="categorie" class="form-label">Catégorie:</label>
					  <select class="form-control" name="categorie" id="categorie" >
					  <option>1</option>
					  <option>2</option>
					  <option>3</option>
					  <option>4</option>
					  </select>
					</div>
					<div class="mb-3">
					  <label for="price" class="form-label">Photo de l'article:</label>
					  <input type="number" class="form-control" name="price"   id="price" >
					</div>
					<div class="mb-3">
					  <label for="releaseDate" class="form-label">Mise à prix:</label>
					  <input type="date" class="form-control"  name="releaseDate" id="releaseDate" >
					</div>
					<div class="mb-3">
					  <label for="pegi" class="form-label">Début de l'enchères</label>
					  <input type="number" class="form-control" name="pegi"  id="pegi" >
					</div>
					<div class="mb-3">
					  <label for="format" class="form-label">Fin de l'enchères</label>
					  <input type="text" class="form-control" name="format"  id="format" >
					</div>
					<div class="mb-3">
					  <label for="version" class="form-label">Retrait</label>
					  <input type="text" class="form-control" name="version"  id="version" >
					</div>
					<div class="mb-3 text-center mt-5">
						<button class="btn btn-primary" type="submit" ><i class="fa-regular fa-floppy-disk"></i></button>
						<button class="btn btn-basic"  type="reset" ><i class="fa-solid fa-eraser"></i></button>
					</div>
			    </form>
			</div>
		</div>			
	</div>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf" %>