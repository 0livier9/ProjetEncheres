package eni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import eni.bll.ArticleVenduManager;
import eni.bo.ArticleVendu;

@WebServlet( "/vente/details")
public class DetailsVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// récupérer le param dans url
			int id = Integer.parseInt(request.getParameter("id") );
			// récupérer l'objet article
			 ArticleVendu article = ArticleVenduManager.getInstance().recupUnArticle(id);
			// transmettre l'objet vers la jsp
			
			request.setAttribute("article", article);
			// forward
			request.getRequestDispatcher("/WEB-INF/pages/details-vente.jsp").forward(request, response);
		} catch (Exception e) {

			response.sendError(404);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
