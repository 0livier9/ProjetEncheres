package eni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import eni.bll.UtilisateurManager;
import eni.bo.Utilisateur;


@WebServlet("/connexion")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = (String) request.getSession().getAttribute("success");
		request.getSession().removeAttribute("success");
		request.setAttribute("success", message);
		request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudoOrEmail = request.getParameter("pseudoOrEmail");
		String password = request.getParameter("password");
		
		Utilisateur utilisateur = UtilisateurManager.getInstance().login(pseudoOrEmail, password);
			
		if (utilisateur == null) {
			request.setAttribute("error", "Le login ou le mot de passe est éronné");
			doGet(request, response);
		}else {
			HttpSession session = request.getSession();
			utilisateur.setMotDePasse("");
			session.setAttribute("utilisateur", utilisateur);
			response.sendRedirect(request.getContextPath()+"/eni-enchere");
		}
		
	}

}
