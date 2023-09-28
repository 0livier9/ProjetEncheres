package eni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import eni.bll.ArticleVenduManager;
import eni.bll.UtilisateurManager;
import eni.bo.ArticleVendu;
import eni.bo.Utilisateur;

@WebServlet("/profil-vendeur")
public class ViewVendeurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Utilisateur vendeur = UtilisateurManager.getInstance().recupererUtilisateurById(id);

		request.setAttribute("vendeur", vendeur);

		request.getRequestDispatcher("/WEB-INF/pages/profil-des-autres.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
