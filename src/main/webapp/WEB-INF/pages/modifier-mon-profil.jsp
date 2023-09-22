<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf" %>
<main class="row">
	<div class="col">
		<div class="row text-center mt-4">
			<h1> Inscription </h1>
		</div>		
		<div class="row mt-5">
			<div class="col-4 offset-4">
			<c:if test="${ !empty error }">
					<div class="alert alert-danger">
						${ error }
					</div>
				</c:if>
				<form method="POST" action="" onsubmit="return confirm('Confirmer mosdification ou suppression ?')" >
					<div class="mb-3">
					  <label for="pseudo" class="form-label">Pseudo</label>
					  <input type="text" class="form-control" value="${ utilisateur.pseudo }" name="pseudo" id="pseudo" placeholder="ex. toto">
					</div>
					<div class="mb-3">
					  <label for="nom" class="form-label">Nom</label>
					  <input type="text" class="form-control" value="${ utilisateur.nom }" name="nom" id="nom" placeholder="ex. toto">
					</div>
					<div class="mb-3">
					  <label for="prenom" class="form-label">Prenom</label>
					  <input type="text" class="form-control" value="${ utilisateur.prenom }" name="prenom" id="prenom" placeholder="ex. toto">
					</div>
					<div class="mb-3">
					  <label for="email" class="form-label">Email</label>
					  <input type="email" class="form-control" value="${ utilisateur.email }" name="email" id="email" placeholder="ex. toto">
					</div>
					<div class="mb-3">
					  <label for="telephone" class="form-label">Telephone</label>
					  <input type="text" class="form-control" value="${ utilisateur.telephone }" name="telephone" id="telephone" placeholder="ex. toto">
					</div>
					<div class="mb-3">
					  <label for="rue" class="form-label">Rue</label>
					  <input type="text" class="form-control" value="${ utilisateur.rue }" name="rue" id="rue" placeholder="ex. toto">
					</div>
					<div class="mb-3">
					  <label for="codePostal" class="form-label">Code Postal</label>
					  <input type="text" class="form-control" value="${ utilisateur.codePostal }" name="codePostal" id="codePostal" placeholder="ex. toto">
					</div>
					<div class="mb-3">
					  <label for="ville" class="form-label">Ville</label>
					  <input type="text" class="form-control" value="${ utilisateur.ville }" name="ville" id="ville" placeholder="ex. toto">
					</div>
					<div class="mb-3">
					  <label for="motDePasse" class="form-label">Mot de passe actuel</label>
					  <input type="password" class="form-control" name="motDePasse" id="motDePasse">
					</div>
					<div class="mb-3">
					  <label for="nouveauMotDePasse" class="form-label">Nouveau mot de passe</label>
					  <input type="password" class="form-control" name="nouveauMotDePasse" id="nouveauMotDePasse">
					</div>
					<div class="mb-3">
					  <label for="ConfirmationMotDePasse" class="form-label">Confirmation mot de passe</label>
					  <input type="password" class="form-control" name="ConfirmationMotDePasse" id="ConfirmationMotDePasse">
					</div>
															
					<div class="mb-3 text-center mt-5">
						<button type="submit" name="modifierOuSupprimer" value="modifier" class="btn btn-danger" >Enregistrer mes modifications</button>
						<button type="submit" name="modifierOuSupprimer" value="supprimer" class="btn btn-danger">Supprimer mon compte</button>
					</div>
				</form>
				
			</div>
		</div>			
	</div>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf" %>