<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf" %>
<main class="row">
	<div class="col-4 offset-4 mt-5 p-5">
		<fieldset style="  border: 1px solid #32fbe2; padding:15px; border-radius:10px; box-shadow: 0 5px 5px #3cf281;">
		
			<legend class="float-none w-auto text-center p-2"><h1> Connexion </h1></legend> 
		
		<div class="row mt-2 mb-4">
			<div class="col-6 offset-3">
				<c:if test="${ !empty success }">
					<div class="alert alert-success">
						${ success }
					</div>
				</c:if>			
				<c:if test="${ !empty error }">
					<div class="alert alert-danger">
						${ error }
					</div>
				</c:if>
				<c:if test="${ param.err == 1 }">
					<div class="alert alert-danger">
						Pour supprimer un jeu faut se connecter
					</div>
				</c:if>
				<form method="post" >
					<div class="mb-3">
					  <label for="pseudoOrEmail" class="form-label">Login (Pseudo ou Email)</label>
					  <input type="text" class="form-control" name="pseudoOrEmail" id="pseudoOrEmail" placeholder="ex. toto">
					</div>
					<div class="mb-3">
					  <label for="password" class="form-label">Mot de passe</label>
					  <input type="password" class="form-control" name="password" id="password" >
					</div>
					<div class="mb-3">
						<a href="${ pageContext.request.contextPath }/forget-password" >Forget password</a>
					</div>
					<button class="btn btn-primary offset-4" role="button" type="submit" >Connexion</button>					
				</form>
			</div>
		</div>	
		</fieldset>		
	</div>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf" %>