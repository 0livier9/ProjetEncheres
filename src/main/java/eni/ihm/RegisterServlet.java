package eni.ihm;

import java.io.IOException;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import eni.bll.UtilisateurManager;
import eni.bll.exception.BLLException;
import eni.bo.Utilisateur;
import eni.dal.jdbc.exception.JDBCException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/inscription")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Utilisateur utilisateur = new Utilisateur(request.getParameter("pseudo"), request.getParameter("nom"),
					request.getParameter("prenom"), request.getParameter("email"), request.getParameter("telephone"),
					request.getParameter("rue"), request.getParameter("codePostal"), request.getParameter("ville"),
					request.getParameter("motDePasse"));

			// user.setConfirmPassword()
			UtilisateurManager.getInstance().inscription(utilisateur);
			// Flash

				HttpSession session = request.getSession();
				utilisateur.setMotDePasse("");
				session.setAttribute("utilisateur", utilisateur);
				response.sendRedirect(request.getContextPath() + "/eni-enchere");
			

		} catch (SQLServerException | JDBCException | BLLException e) {

			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
		}

	}

}
