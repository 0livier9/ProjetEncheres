package eni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/profil")
public class ViewProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("utilisateur")!=null ){
			request.getRequestDispatcher("/WEB-INF/pages/profil.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/WEB-INF/pages/utilisateur.jsp").forward(request, response);
		}
		
		
		
		
//		
//	
//		
	
	}
	
}
