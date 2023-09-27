package eni.bll;

import java.util.ArrayList;
import java.util.List;

import eni.bo.ArticleVendu;
import eni.bo.Enchere;
import eni.dal.ArticleVenduDao;
import eni.dal.DaoFactory;
import eni.dal.EnchereDao;


public class ArticleVenduManager {

	private static ArticleVenduManager instance;
	private ArticleVenduManager() {}
	public static ArticleVenduManager getInstance() {
		if(instance==null) instance = new ArticleVenduManager();
		return instance;
	}
	private ArticleVenduDao ArticleVenduDao = DaoFactory.getArticleVendueDao();
	public ArticleVendu recupUnArticle(int id) {
		return ArticleVenduDao.findOne(id);
	}
	
	public List<ArticleVendu> recupTousLesArticles() {
		return ArticleVenduDao.findAll();
	}
	
	public void ajouterUneVente(ArticleVendu article) {
		ArticleVenduDao.save(article);				  	
	}
	public void modifierUnArticle(ArticleVendu article) {
		ArticleVenduDao.modify(article);				  	
	}
	
	public void supprimerUnArticle(int id) {
		ArticleVenduDao.remove(id);
	}
	public List<ArticleVendu> rechercheUnArticle(String query) {
		return  ArticleVenduDao.findByName(query) ;
	}
	public List<ArticleVendu> rechercheUnArticleParCate(int categoryId) {
		return  ArticleVenduDao.findByCat(categoryId) ;
	}
	
	public void modifierEtatVente(String etatVente, int noArticle) {
		ArticleVenduDao.modifyEtat(etatVente, noArticle);
	}
	
	public List<ArticleVendu> rechercheUnArticleParEtat (String etatVente,int noAcheteur){
		return ArticleVenduDao.findByEtatVente(etatVente, noAcheteur);
	}
	public List<ArticleVendu> findbyUser ( int noUtilisateur){
		return ArticleVenduDao.findbyUser(noUtilisateur);
	}
	public List<ArticleVendu> findbyVendeur (String etatVente,int noVendeur ){
		return ArticleVenduDao.findByVendeur(etatVente, noVendeur);
	}
	public List<ArticleVendu> findbyetat(){
		return ArticleVenduDao.findbyetat();
	}
	
	
}
