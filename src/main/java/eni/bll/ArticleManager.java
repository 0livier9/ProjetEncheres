package eni.bll;

import java.util.List;

import eni.bo.ArticleVendu;
import eni.dal.ArticleDao;


public class ArticleManager {
	// début Singleton
	private static ArticleManager instance;
	private ArticleManager() {}
	public static ArticleManager getInstance() {
		if(instance==null) instance = new ArticleManager();
		return instance;
	}
	// Fin Singleton
	private ArticleDao ArticleDao = DaoFactory.getGameDao();
	// début la logique métier
	public ArticleVendu recupUnJeu(int id) {
		return ArticleDao.findOne(id);
	}
	
	public List<ArticleVendu> recupTousLesJeux() {
		return ArticleDao.findAll();
	}
	
	public void ajouterUnJeu(ArticleVendu article) {
		
		// datas validation !!
		ArticleDao.save(article);				  	
	}
	public void modifierUnJeu(ArticleVendu article) {
		// datas validation !!
		ArticleDao.modify(article);				  	
	}
	
	public void supprimerUnJeu(int id) {
		ArticleDao.remove(id);
	}
	public List<ArticleVendu> rechercheUnJeu(String query) {
		return  ArticleDao.findByName(query) ;
	}
	
	// finde la logique métier
	
	
}
