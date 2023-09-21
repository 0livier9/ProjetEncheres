package eni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import eni.bll.UtilisateurManager;
import eni.bll.exception.BLLException;
import eni.bo.Utilisateur;
import eni.dal.jdbc.exception.JDBCException;

@WebServlet("/modification")
public class ModifyProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/modifier-mon-profil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String action = request.getParameter("modifier");
			
			if (action.equals("modifier")) {
				
				HttpSession session = request.getSession();
				Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
				int noUtilisateur = utilisateur.getNoUtilisateur();
				int credit = utilisateur.getCredit();
				boolean administrateur = utilisateur.isAdministrateur();
			
				utilisateur = new Utilisateur(noUtilisateur, request.getParameter("pseudo"), request.getParameter("nom"),
						request.getParameter("prenom"), request.getParameter("email"), request.getParameter("telephone"),
						request.getParameter("rue"), request.getParameter("codePostal"), request.getParameter("ville"),
						request.getParameter("motDePasse"), credit, administrateur);
				
				String nouveauMotDePasse="";
			
				if (request.getParameter("nouveauMotDePasse").isEmpty()) {
					nouveauMotDePasse = request.getParameter("motDePasse");
				}else {
					nouveauMotDePasse = request.getParameter("nouveauMotDePasse");
				}
				
				UtilisateurManager.getInstance().modificationUtilisateur(utilisateur, nouveauMotDePasse);
				
				session.setAttribute("utilisateur", utilisateur);
				
				response.sendRedirect( request.getContextPath() +"/modification");
				
			}else {
				
				String pseudo = request.getParameter("pseudo");	
				String motDePasse = request.getParameter("motDePasse");
				
				// supprimer un utilisateur
				
				UtilisateurManager.getInstance().supprimerUnUtilisateur(pseudo, motDePasse);
				
				HttpSession session = request.getSession();
				session.invalidate();
				response.sendRedirect( request.getContextPath() +"/Accueil");
			}
			
			
		}catch (BLLException e) {

			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
		}
	}

}

