package eni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;


import eni.bll.EnchereManager;
import eni.bo.ArticleVendu;
import eni.bo.Categorie;
import eni.bo.Enchere;
import eni.bo.Utilisateur;


@WebServlet("/encheres/ajouter")
public class AddEnchereServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// afficher la page d'ajout
		request.getRequestDispatcher("/WEB-INF/pages/enchere-add.jsp")
			   .forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// get data from param
			Utilisateur user = null;
			
			String nom =   request.getParameter("nom_article");
			String description =   request.getParameter("description");			
			LocalDate dateDebutEnchere =  LocalDate.parse(request.getParameter("date_debut_encheres")) ;
			LocalDate dateFinEnchere =  LocalDate.parse(request.getParameter("date_fin_encheres")) ;
			int prixIni =  Integer.parseInt(request.getParameter("prix_initial")) ;
			Categorie categorie = null;
			ArticleVendu article= new ArticleVendu( nom, description, dateDebutEnchere, dateFinEnchere, prixIni, user, categorie );
			user = article.getUser();
			categorie = article.getCategorie();
			// create Article instance
			Enchere enchere = new Enchere(  article, user );
			
			// Add
			EnchereManager.getInstance().ajouterUneEnchere(enchere);
			// redirect
			response.sendRedirect( request.getContextPath() +"/encheres.jsp");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
