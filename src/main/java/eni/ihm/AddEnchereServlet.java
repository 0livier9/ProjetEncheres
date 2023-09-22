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
import eni.bo.ArticleVendu;
import eni.bo.Enchere;
import eni.bo.Utilisateur;

@WebServlet("/encherir")
public class AddEnchereServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

		int noUtilisateur = utilisateur.getNoUtilisateur(); // On récupérer le numéro de l'utilisateur
		
		
		
		request.getRequestDispatcher("/WEB-INF/pages/mon-profil.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		int id = (int) session.getAttribute("id-article");

		ArticleVendu article = ArticleVenduManager.getInstance().recupUnArticle(id);
		
		LocalDate dateEnchere = LocalDate.now();
		int montantEnchere = Integer.parseInt(request.getParameter("montantEnchere"));
		
		Enchere enchere = new Enchere(utilisateur, article, dateEnchere, montantEnchere);
		
		EnchereManager.getInstance().ajouterUneEncheree(enchere);
		
		
		}
	
	
	

}
