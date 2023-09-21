package eni.ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import eni.bll.ArticleVenduManager;
import eni.bll.EnchereManager;
import eni.bll.UtilisateurManager;
import eni.bo.ArticleVendu;
import eni.bo.Enchere;
import eni.bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Accueil")
public class ListEncheresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ArticleVendu> articles = null;
	
		if(request.getParameter("q")!=null) {
			articles = ArticleVenduManager.getInstance().rechercheUnArticle(request.getParameter("q"));
		}else {
			articles = ArticleVenduManager.getInstance().recupTousLesArticles();
		}		
		request.setAttribute("articles", articles);
		request.setAttribute("annee", LocalDate.now().getYear());
		request.getRequestDispatcher("/WEB-INF/pages/encheres.jsp")
		       .forward(request, response);
	}
}
