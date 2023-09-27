package eni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import eni.bll.ArticleVenduManager;
import eni.bll.CategorieManager;
import eni.bll.EnchereManager;
import eni.bll.exception.BLLException;
import eni.bo.ArticleVendu;
import eni.bo.Categorie;
import eni.bo.Enchere;
import eni.bo.Utilisateur;

@WebServlet( "/vente/details")
public class DetailsVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
			// récupérer le param dans url
			int id = Integer.parseInt(request.getParameter("id") );
			// récupérer l'objet article
			HttpSession session = request.getSession();
			session.setAttribute("id-article", id);
			
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
			
			 ArticleVendu article = ArticleVenduManager.getInstance().recupUnArticle(id);
			 Enchere enchere = EnchereManager.getInstance().trouverUneEnchere(id);
			// transmettre l'objet vers la jsp
			request.setAttribute("enchere", enchere);
			request.setAttribute("article", article);
			request.setAttribute("utilisateur", utilisateur);
			
		
			// forward
			request.getRequestDispatcher("/WEB-INF/pages/details-vente.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action=request.getParameter("encherirOuModifier");
		
		
			HttpSession session = request.getSession();
			
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
			int id = (int) session.getAttribute("id-article");
			
			ArticleVendu article = ArticleVenduManager.getInstance().recupUnArticle(id);
			
			request.setAttribute("article", article);
			int numeroArticle = Integer.parseInt(request.getParameter("id"));
			
			if (action.equals("encherir")) {
			
			LocalDate dateEnchere = LocalDate.now();
			int montantEnchere = Integer.parseInt(request.getParameter("montantEnchere"));
			
			Enchere enchere = new Enchere(utilisateur, article, dateEnchere, montantEnchere);
			
			try {
				
				EnchereManager.getInstance().ajouterUneEnchere(enchere);
				
			} catch (BLLException e) {
//				enchere.setMontantEnchere(ancienneEnchere);
				request.setAttribute("enchere", enchere);
				request.setAttribute("error", e.getMessage());
				
				doGet(request, response);
				e.printStackTrace();
				return;
			}
			request.setAttribute("enchere", enchere);
			request.getRequestDispatcher("/WEB-INF/pages/details-vente.jsp").forward(request, response);
		}
		else {
			
			List<Categorie> categories = CategorieManager.getInstance().recupTousLesCategories();
			
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("/WEB-INF/pages/modifier-mon-enchere.jsp").forward(request, response);
		}
		
	
	}
}
