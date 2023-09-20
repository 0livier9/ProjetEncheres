package eni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import eni.bll.ArticleVenduManager;
import eni.bll.CategorieManager;
import eni.bll.EnchereManager;
import eni.bo.ArticleVendu;
import eni.bo.Categorie;
import eni.bo.Enchere;
import eni.bo.Utilisateur;

@WebServlet("/encheres/ajouter")
public class AddArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// afficher la page d'ajout
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    HttpSession session = request.getSession();

	    Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

	    int noUtilisateur = utilisateur.getNoUtilisateur();  //  On récupérer le numéro de l'utilisateur

	    String rue = utilisateur.getRue();
	    String codePostal = utilisateur.getCodePostal();
	    String ville = utilisateur.getVille();

	    List<Categorie> categories = CategorieManager.getInstance().recupTousLesCategories();

	    // On met  les catégories dans la requête
	    request.setAttribute("categories", categories);

	    request.setAttribute("rue", rue);
	    request.setAttribute("code_postal", codePostal);
	    request.setAttribute("ville", ville);

	    // On met le numéro de l'utilisateur dans la requête
	    request.setAttribute("no_utilisateur", noUtilisateur);

	    request.getRequestDispatcher("/WEB-INF/pages/article-add.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// get data from param
		    HttpSession session = request.getSession();
	        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
	        int noUtilisateur = utilisateur.getNoUtilisateur();
	        
			Categorie categorie = new Categorie();

			System.out.println(noUtilisateur);
			String nom = request.getParameter("nom_article");
			String description = request.getParameter("description");
			LocalDate dateDebutEnchere = LocalDate.parse(request.getParameter("date_fin_encheres"));
			LocalDate dateFinEnchere = LocalDate.parse(request.getParameter("date_fin_encheres"));
			int prixInitial = Integer.parseInt(request.getParameter("prix_initial"));
			String etatVente = request.getParameter("etat_vente");
			int noCategorie = categorie.getNoCategorie();

			// create Article instance
			ArticleVendu article = new ArticleVendu(nom, description, dateDebutEnchere, dateFinEnchere, prixInitial, 0,
					noUtilisateur, noCategorie, etatVente);
			// Add 
			ArticleVenduManager.getInstance().ajouterUneVente(article);
			// redirect

			response.sendRedirect(request.getContextPath() + "/encheres.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
