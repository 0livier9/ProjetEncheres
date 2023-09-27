package eni.ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import eni.bll.ArticleVenduManager;
import eni.bll.CategorieManager;
import eni.bo.ArticleVendu;
import eni.bo.Categorie;
import eni.bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("")
public class ListEncheresServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Categorie> categories = CategorieManager.getInstance().recupTousLesCategories();
        List<ArticleVendu> articles = new ArrayList<>();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
        int noUtilisateur = (utilisateur != null) ? utilisateur.getNoUtilisateur() : -1;

        String selectedCategory = request.getParameter("categories");
        String mvec = request.getParameter("mvec");
        String etat = request.getParameter("etat");
        String ventes = request.getParameter("ventes");
        String q = request.getParameter("q");
        String mvt = request.getParameter("mvt");
        String vnd = request.getParameter("vnd");
        String eo = request.getParameter("eo");

        if (q != null) {
            articles = ArticleVenduManager.getInstance().rechercheUnArticle(q);
        } else if (selectedCategory != null && !selectedCategory.isEmpty()
                && !selectedCategory.equals("Selectionner une categorie")) {
            int categoryId = Integer.parseInt(selectedCategory);
            articles = ArticleVenduManager.getInstance().rechercheUnArticleParCate(categoryId);
        } else if (mvec != null) {
            articles = ArticleVenduManager.getInstance().findbyUser(noUtilisateur);
        } else if (eo != null) {
            articles = ArticleVenduManager.getInstance().findbyetat();
            System.out.println(eo + 1);
        } else if (etat != null) {
            articles = ArticleVenduManager.getInstance().rechercheUnArticleParEtat(etat, noUtilisateur);
            System.out.println(etat);
        } else if (ventes != null) {
            articles = ArticleVenduManager.getInstance().rechercheUnArticleParEtat(ventes, noUtilisateur);
        } else if (mvt != null || vnd != null) {
            articles = ArticleVenduManager.getInstance().findbyVendeur((mvt != null) ? mvt : vnd, noUtilisateur);
        } else {
            articles = ArticleVenduManager.getInstance().recupTousLesArticles();
        }

        request.setAttribute("categories", categories);
        request.setAttribute("articles", articles);
        request.setAttribute("annee", LocalDate.now().getYear());
        request.getRequestDispatcher("/WEB-INF/pages/encheres.jsp").forward(request, response);
    }
}
