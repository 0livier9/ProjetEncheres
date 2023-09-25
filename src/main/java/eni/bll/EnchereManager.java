package eni.bll;

import java.util.List;

import eni.bo.ArticleVendu;
import eni.bo.Enchere;
import eni.dal.DaoFactory;
import eni.dal.EnchereDao;
import jakarta.servlet.http.HttpSession;


public class EnchereManager {
	// début Singleton
	private static EnchereManager instance;
	private EnchereManager() {}
	public static EnchereManager getInstance() {
		if(instance==null) instance = new EnchereManager();
		return instance;
	}
	// Fin Singleton
	
	private EnchereDao EnchereDao = DaoFactory.getEnchereDao();
	
	
	public List<Enchere> recupTousLesEncheres() {
		return EnchereDao.findAll();
	}
	
	public void trouverUneEnchere(Enchere enchere) {	
		
		Enchere ancienneEnchere = EnchereDao.findOne(enchere.getArticle().getNoArticle());
		
		if (ancienneEnchere==null) {
			EnchereDao.save(enchere);	
		}else {
			EnchereDao.modify(enchere);	
		}
					  	
	}
//	public void modifierUneEnchere(Enchere enchere) {
//		// datas validation !!
//		EnchereDao.modify(enchere);				  	
//	}
//	
//	public void supprimerUneEnchere(int id) {
//		EnchereDao.remove(id);
//	}
//	public List<Enchere> rechercheUneEnchere(String query) {
//		return  EnchereDao.findByName(query) ;
//	}
	
	// finde la logique métier
	
	
}
