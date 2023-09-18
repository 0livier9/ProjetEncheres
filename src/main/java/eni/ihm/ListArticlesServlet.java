package eni.ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import eni.bll.ArticleManager;
import eni.bo.ArticleVendu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/eni-enchere")
public class ListArticlesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ArticleVendu> games = null;
		if(request.getParameter("q")!=null) {
			games = ArticleManager.getInstance().rechercheUnJeu(request.getParameter("q"));
		}else {
			games = ArticleManager.getInstance().recupTousLesArticles();
		}		
		request.setAttribute("games", games);
		request.setAttribute("annee", LocalDate.now().getYear());
		request.getRequestDispatcher("/WEB-INF/pages/games.jsp")
		       .forward(request, response);
	}
}
