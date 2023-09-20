package eni.bll;

import java.util.List;

import eni.bo.ArticleVendu;
import eni.bo.Enchere;
import eni.dal.DaoFactory;
import eni.dal.EnchereDao;


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
	// début la logique métier
	public Enchere recupUneEnchere(int id) {
		return EnchereDao.findOne(id);
	}
	
	public List<Enchere> recupTousLesEncheres() {
		return EnchereDao.findAll();
	}
	
	public void ajouterUneEncheree(Enchere enchere) {
		
		// datas validation !!
		EnchereDao.save(enchere);				  	
	}
	public void modifierUneEnchere(Enchere enchere) {
		// datas validation !!
		EnchereDao.modify(enchere);				  	
	}
	
	public void supprimerUneEnchere(int id) {
		EnchereDao.remove(id);
	}
	public List<Enchere> rechercheUneEnchere(String query) {
		return  EnchereDao.findByName(query) ;
	}
	
	// finde la logique métier
	
	
}
