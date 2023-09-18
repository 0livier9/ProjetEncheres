package eni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import eni.bll.ArticleManager;
import eni.bo.ArticleVendu;


@WebServlet("/articles/ajouter")
public class AddArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// afficher la page d'ajout
		request.getRequestDispatcher("/WEB-INF/pages/article-add.jsp")
			   .forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// get data from param
			String nom =   request.getParameter("nom_article");
			String description =   request.getParameter("description");			
			LocalDate dateDebutEnchere =  LocalDate.parse(request.getParameter("date_debut_encheres")) ;
			LocalDate dateFinEnchere =  LocalDate.parse(request.getParameter("date_fin_encheres")) ;
			int prixIni =  Integer.parseInt(request.getParameter("prix_initial")) ;
			int prixFinal =  Integer.parseInt(request.getParameter("prix_vente")) ;
	
			// create Article instance
			ArticleVendu article = new ArticleVendu( prixFinal, nom, description, dateDebutEnchere, dateFinEnchere, prixIni, prixFinal, null );
			
			// Add
			ArticleManager.getInstance().ajouterUnJeu(article);
			// redirect
			response.sendRedirect( request.getContextPath() +"/encheres");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
