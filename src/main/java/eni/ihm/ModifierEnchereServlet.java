package eni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import eni.bll.ArticleVenduManager;
import eni.bll.CategorieManager;
import eni.bo.ArticleVendu;
import eni.bo.Categorie;
import eni.bo.Utilisateur;

@WebServlet("/modificationEnchere")
public class ModifierEnchereServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("no_article"));
		
		ArticleVendu article = ArticleVenduManager.getInstance().recupUnArticle(id);
		
		String nomArticle = request.getParameter("nom_article");
		String description = request.getParameter("description");
		
		int noCat = Integer.parseInt(request.getParameter("categories")) ;
		String libelleCategorie = request.getParameter("libelle");
		
		Categorie categorie = new Categorie( noCat,libelleCategorie);
		
		int prixInitial = Integer.parseInt(request.getParameter("prix_initial"));
		LocalDate dateDebutEnchere = LocalDate.parse(request.getParameter("date_debut_encheres"));
		LocalDate dateFinEnchere = LocalDate.parse(request.getParameter("date_fin_encheres"));
		
		article.setNomArticle(nomArticle);
		article.setDescription(description);
		article.setCategorie(categorie);
		article.setPrixInitial(prixInitial);
		article.setDateDebutEncheres(dateDebutEnchere);
		article.setDateFinEncheres(dateFinEnchere);
		
		ArticleVenduManager.getInstance().modifierUnArticle(article);
		
		response.sendRedirect(request.getContextPath()+"/vente/details?id="+article.getNoArticle());
	}

}

