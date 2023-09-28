package eni.bll;

import java.util.List;

import eni.bo.Categorie;
import eni.dal.DaoFactory;
import eni.dal.CategorieDao;

public class CategorieManager {

	private static CategorieManager instance;

	private CategorieManager() {
	}

	public static CategorieManager getInstance() {
		if (instance == null)
			instance = new CategorieManager();
		return instance;
	}

	private CategorieDao CategorieDao = DaoFactory.getCategorieDao();

	public Categorie recupUneCategorie(int id) {
		return CategorieDao.findOne(id);
	}

	public List<Categorie> recupTousLesCategories() {
		return CategorieDao.findAll();
	}

	public void ajouterUneCategorie(Categorie categorie) {

		CategorieDao.save(categorie);
	}

	public void modifierUneCategorie(Categorie categorie) {

		CategorieDao.modify(categorie);
	}

	public void supprimerUneCategorie(int id) {
		CategorieDao.remove(id);
	}

}
