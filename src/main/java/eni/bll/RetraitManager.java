package eni.bll;

import java.util.List;

import eni.bo.Retrait;
import eni.dal.DaoFactory;
import eni.dal.RetraitDao;

public class RetraitManager {

	private static RetraitManager instance;

	private RetraitManager() {
	}

	public static RetraitManager getInstance() {
		if (instance == null)
			instance = new RetraitManager();
		return instance;
	}

	private RetraitDao RetraitDao = DaoFactory.getRetraitDao();

	public List<Retrait> recupTousLesRetrait() {
		return RetraitDao.findAll();
	}

	public void ajouterUneRetrait(Retrait retrait) {

		RetraitDao.save(retrait);
	}

	public void supprimerUneRetrait(int id) {
		RetraitDao.remove(id);
	}

}
