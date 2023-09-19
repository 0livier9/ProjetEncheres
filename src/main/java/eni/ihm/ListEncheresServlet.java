package eni.ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


import eni.bll.EnchereManager;
import eni.bo.Enchere;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/eni-enchere")
public class ListEncheresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Enchere> encheres = null;
		if(request.getParameter("q")!=null) {
			encheres = EnchereManager.getInstance().rechercheUneEnchere(request.getParameter("q"));
		}else {
			encheres = EnchereManager.getInstance().recupTousLesEncheres();
		}		
		request.setAttribute("encheres", encheres);
		request.setAttribute("annee", LocalDate.now().getYear());
		request.getRequestDispatcher("/WEB-INF/pages/encheres.jsp")
		       .forward(request, response);
	}
}
