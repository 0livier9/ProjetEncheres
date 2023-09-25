package eni.bll;

import java.util.List;

import eni.bo.ArticleVendu;
import eni.bo.Enchere;
import eni.dal.ArticleVenduDao;
import eni.dal.DaoFactory;
import eni.dal.EnchereDao;


public class ArticleVenduManager {
	// début Singleton
	private static ArticleVenduManager instance;
	private ArticleVenduManager() {}
	public static ArticleVenduManager getInstance() {
		if(instance==null) instance = new ArticleVenduManager();
		return instance;
	}
	// Fin Singleton
	private ArticleVenduDao ArticleVenduDao = DaoFactory.getArticleVendueDao();
	// début la logique métier
	public ArticleVendu recupUnArticle(int id) {
		return ArticleVenduDao.findOne(id);
	}
	
	public List<ArticleVendu> recupTousLesArticles() {
		return ArticleVenduDao.findAll();
	}
	
	public void ajouterUneVente(ArticleVendu article) {
		
		// datas validation !!
		ArticleVenduDao.save(article);				  	
	}
	public void modifierUnArticle(ArticleVendu article) {
		// datas validation !!
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
	// finde la logique métier
	
	
}
