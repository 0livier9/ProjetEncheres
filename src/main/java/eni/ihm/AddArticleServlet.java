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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

		int noUtilisateur = utilisateur.getNoUtilisateur();

		String rue = utilisateur.getRue();
		String codePostal = utilisateur.getCodePostal();
		String ville = utilisateur.getVille();

		List<Categorie> categories = CategorieManager.getInstance().recupTousLesCategories();

		request.setAttribute("categories", categories);

		request.setAttribute("rue", rue);
		request.setAttribute("code_postal", codePostal);
		request.setAttribute("ville", ville);

		request.setAttribute("no_utilisateur", noUtilisateur);

		request.getRequestDispatcher("/WEB-INF/pages/article-add.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			HttpSession session = request.getSession();
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

			int noCat = Integer.parseInt(request.getParameter("categories"));
			String libelleCategorie = request.getParameter("libelle");

			Categorie categorie = new Categorie(noCat, libelleCategorie);

			String nom = request.getParameter("nom_article");
			String description = request.getParameter("description");
			LocalDate dateDebutEnchere = LocalDate.parse(request.getParameter("date_debut_encheres"));
			LocalDate dateFinEnchere = LocalDate.parse(request.getParameter("date_fin_encheres"));
			int prixInitial = Integer.parseInt(request.getParameter("prix_initial"));
			String etatVente = request.getParameter("etat_vente");

			ArticleVendu article = new ArticleVendu(nom, description, dateDebutEnchere, dateFinEnchere, prixInitial, 0,
					utilisateur, categorie, etatVente);

			ArticleVenduManager.getInstance().ajouterUneVente(article);

			response.sendRedirect(request.getContextPath() + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
