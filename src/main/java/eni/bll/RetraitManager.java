package eni.bll;

import java.util.List;

import eni.bo.Retrait;
import eni.dal.DaoFactory;
import eni.dal.RetraitDao;


public class RetraitManager {
	// début Singleton
	private static RetraitManager instance;
	private RetraitManager() {}
	public static RetraitManager getInstance() {
		if(instance==null) instance = new RetraitManager();
		return instance;
	}
	// Fin Singleton
	private RetraitDao RetraitDao = DaoFactory.getRetraitDao();
	// début la logique métier
	public Retrait recupUneRetrait(int id) {
		return RetraitDao.findOne(id);
	}
	
	public List<Retrait> recupTousLesRetrait() {
		return RetraitDao.findAll();
	}
	
	public void ajouterUneRetrait(Retrait retrait) {
		
		// datas validation !!
		RetraitDao.save(retrait);				  	
	}
	public void modifierUneRetrait(Retrait retrait) {
		// datas validation !!
		RetraitDao.modify(retrait);				  	
	}
	
	public void supprimerUneRetrait(int id) {
		RetraitDao.remove(id);
	}
	public List<Retrait> rechercheUneRetrait(String query) {
		return  RetraitDao.findByName(query) ;
	}
	
	// finde la logique métier
	
	
}
