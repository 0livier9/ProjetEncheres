package eni.ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import eni.bll.ArticleVenduManager;
import eni.bll.CategorieManager;
import eni.bll.EnchereManager;
import eni.bll.UtilisateurManager;
import eni.bo.ArticleVendu;
import eni.bo.Categorie;
import eni.bo.Enchere;
import eni.bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("")
public class ListEncheresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Categorie> categories = CategorieManager.getInstance().recupTousLesCategories();

		List<ArticleVendu> articles = null;
		String selectedCategory = request.getParameter("categories");
		String mvec = request.getParameter("mvec");
		String ec = request.getParameter("ec");

		if (request.getParameter("q") != null) {
			articles = ArticleVenduManager.getInstance().rechercheUnArticle(request.getParameter("q"));

		}
		if (selectedCategory != null && !selectedCategory.isEmpty() && !selectedCategory.equals("Selectionner une categorie")) {

		} else if (selectedCategory != null && !selectedCategory.isEmpty()
				&& !selectedCategory.equals("Selectionner une categorie")) {

			int categoryId = Integer.parseInt(selectedCategory);
			articles = ArticleVenduManager.getInstance().rechercheUnArticleParCate(categoryId);

		} else if (mvec != null) {
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
			int noUtilisateur = utilisateur.getNoUtilisateur();
			articles = ArticleVenduManager.getInstance().findbyUser(noUtilisateur);
		} else if (ec != null) {
			articles= ArticleVenduManager.getInstance().rechercheUnArticleParEtat(ec);
			System.out.println(articles);
		}

		else {
			articles = ArticleVenduManager.getInstance().recupTousLesArticles();
			
		}

		request.setAttribute("categories", categories);
		request.setAttribute("articles", articles);

		request.setAttribute("annee", LocalDate.now().getYear());
		request.getRequestDispatcher("/WEB-INF/pages/encheres.jsp").forward(request, response);

	}
}
