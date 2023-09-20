package eni.bll;

import java.util.List;

import eni.bo.Categorie;
import eni.dal.DaoFactory;
import eni.dal.CategorieDao;


public class CategorieManager {
	// début Singleton
	private static CategorieManager instance;
	private CategorieManager() {}
	public static CategorieManager getInstance() {
		if(instance==null) instance = new CategorieManager();
		return instance;
	}
	// Fin Singleton
	private CategorieDao CategorieDao = DaoFactory.getCategorieDao();
	// début la logique métier
	public Categorie recupUneCategorie(int id) {
		return CategorieDao.findOne(id);
	}
	
	public List<Categorie> recupTousLesCategories() {
		return CategorieDao.findAll();
	}
	
	public void ajouterUneCategorie(Categorie categorie) {
		
		// datas validation !!
		CategorieDao.save(categorie);				  	
	}
	public void modifierUneCategorie(Categorie categorie) {
		// datas validation !!
		CategorieDao.modify(categorie);				  	
	}
	
	public void supprimerUneCategorie(int id) {
		CategorieDao.remove(id);
	}
	public List<Categorie> rechercheUneCategorie(String query) {
		return  CategorieDao.findByName(query) ;
	}
	
	// finde la logique métier
	
	
}
