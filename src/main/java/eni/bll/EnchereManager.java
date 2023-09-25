package eni.bll;

import java.util.List;

import eni.bll.exception.BLLException;
import eni.bo.ArticleVendu;
import eni.bo.Enchere;
import eni.dal.DaoFactory;
import eni.dal.EnchereDao;
import eni.dal.UtilisateurDao;
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
	
	private EnchereDao enchereDao = DaoFactory.getEnchereDao();
	private UtilisateurDao utilisateurDao = DaoFactory.getUtilisateurDao();
	
	
	public List<Enchere> recupTousLesEncheres() {
		return enchereDao.findAll();
	}
	
	public void ajouterUneEnchere(Enchere enchere) throws BLLException {	
		
		Enchere ancienneEnchere = enchereDao.findOne(enchere.getArticle().getNoArticle());
		
		if (ancienneEnchere==null) {
			enchereDao.save(enchere);	
		}else {
			if (ancienneEnchere.getUser().getNoUtilisateur()==enchere.getUser().getNoUtilisateur()) {
				throw new BLLException("Vous avez déjà la meilleur enchère");
			}
			if (enchere.getMontantEnchere()>ancienneEnchere.getMontantEnchere() && enchere.getUser().getCredit()>=enchere.getMontantEnchere()) {
				int nouveauCredit = enchere.getUser().getCredit()-enchere.getMontantEnchere();
				utilisateurDao.modifyCredit(nouveauCredit,enchere.getUser().getNoUtilisateur());
				nouveauCredit=ancienneEnchere.getUser().getCredit()+ancienneEnchere.getMontantEnchere();
				utilisateurDao.modifyCredit(nouveauCredit, ancienneEnchere.getUser().getNoUtilisateur());
			}else {
				throw new BLLException("Merci de mettre une enchère supérieure à la précédente ");
			}
		}
		
					  	
	}
	
	public Enchere trouverUneEnchere(int noArticle) {
		Enchere enchere = enchereDao.findOne(noArticle);
		return enchere;
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
