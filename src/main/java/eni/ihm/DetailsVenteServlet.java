package eni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

import eni.bll.ArticleVenduManager;
import eni.bll.EnchereManager;
import eni.bll.exception.BLLException;
import eni.bo.ArticleVendu;
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
			
			 ArticleVendu article = ArticleVenduManager.getInstance().recupUnArticle(id);
			 Enchere enchere = EnchereManager.getInstance().trouverUneEnchere(id);
			// transmettre l'objet vers la jsp
			request.setAttribute("enchere", enchere);
			request.setAttribute("article", article);
		
			// forward
			request.getRequestDispatcher("/WEB-INF/pages/details-vente.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		int id = (int) session.getAttribute("id-article");
		
		ArticleVendu article = ArticleVenduManager.getInstance().recupUnArticle(id);
		
		LocalDate dateEnchere = LocalDate.now();
		int montantEnchere = Integer.parseInt(request.getParameter("montantEnchere"));
		
		Enchere enchere = new Enchere(utilisateur, article, dateEnchere, montantEnchere);
		
		request.setAttribute("article", article);
		int numeroArticle = Integer.parseInt(request.getParameter("id"));
			
		try {
			
			EnchereManager.getInstance().ajouterUneEnchere(enchere);
			
		} catch (BLLException e) {
//			enchere.setMontantEnchere(ancienneEnchere);
			request.setAttribute("enchere", enchere);
			request.setAttribute("error", e.getMessage());
			
			doGet(request, response);
			e.printStackTrace();
			return;
		}
		request.setAttribute("enchere", enchere);
		request.getRequestDispatcher("/WEB-INF/pages/details-vente.jsp").forward(request, response);
	}
}
