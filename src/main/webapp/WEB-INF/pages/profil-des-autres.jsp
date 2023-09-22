<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf" %>
<main class="row">
	<div class="col">
		<div class="row text-center mt-4">
			<h1> Inscription </h1>
		</div>		
		<div class="row mt-5">
			<div class="col-8 offset-2">
					<div class="mb-3">
					  <label for="pseudo" class="form-label">Pseudo</label>
					  <input type="text" class="form-control" readonly="readonly" value="${ vendeur.pseudo }" name="pseudo" id="pseudo" placeholder="ex. toto">
					</div>
					<div class="mb-3">
					  <label for="nom" class="form-label">Nom</label>
					  <input type="text" class="form-control" readonly="readonly" value="${ vendeur.nom }" name="nom" id="nom" placeholder="ex. toto">
					</div>
					<div class="mb-3">
					  <label for="prenom" class="form-label">Prenom</label>
					  <input type="text" class="form-control" readonly="readonly" value="${ vendeur.prenom }" name="prenom" id="prenom" placeholder="ex. toto">
					</div>
					<div class="mb-3">
					  <label for="email" class="form-label">Email</label>
					  <input type="email" class="form-control" readonly="readonly" value="${ vendeur.email }" name="email" id="email" placeholder="ex. toto">
					</div>
					<div class="mb-3">
					  <label for="telephone" class="form-label">Telephone</label>
					  <input type="text" class="form-control" readonly="readonly" value="${ vendeur.telephone }" name="telephone" id="telephone" placeholder="ex. toto">
					</div>
					<div class="mb-3">
					  <label for="rue" class="form-label">Rue</label>
					  <input type="text" class="form-control" readonly="readonly" value="${ vendeur.rue }" name="rue" id="rue" placeholder="ex. toto">
					</div>
					<div class="mb-3">
					  <label for="codePostal" class="form-label">Code Postal</label>
					  <input type="text" class="form-control" readonly="readonly" value="${ vendeur.codePostal }" name="codePostal" id="codePostal" placeholder="ex. toto">
					</div>
					<div class="mb-3">
					  <label for="ville" class="form-label">Ville</label>
					  <input type="text" class="form-control" readonly="readonly" value="${ vendeur.ville }" name="ville" id="ville" placeholder="ex. toto">
					</div>			
			</div>
		</div>			
	</div>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf" %>