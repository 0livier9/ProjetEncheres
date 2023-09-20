package eni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import eni.bll.UtilisateurManager;

@WebServlet("/modification")
public class ModifyProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/modifier-mon-profil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String action = request.getParameter("modifier");
			
			if (action=="modifier") {
				
				
			}else {
				// récupérer le param dans url
				String pseudo = request.getParameter("pseudo");	
				String motDePasse = request.getParameter("motDePasse");
				
				// supprimer un utilisateur
				
				UtilisateurManager.getInstance().supprimerUnUtilisateur(pseudo, motDePasse);
				// redirect
				
				HttpSession session = request.getSession();
				session.invalidate();
				response.sendRedirect( request.getContextPath() +"/eni-enchere");
			}
			
			
		}catch (Exception e) {
			response.sendError(404);
		}
	}

}

