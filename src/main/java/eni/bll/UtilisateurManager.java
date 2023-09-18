package eni.bll;

import eni.dal.DaoFactory;
import eni.dal.UtilisateurDao;

public class UtilisateurManager {

private static UtilisateurManager instance;
	
	private UtilisateurManager() {}
	
	public static UtilisateurManager getInstance() {
		if(instance==null) instance = new UtilisateurManager();
		return instance;
	}
	
	private UtilisateurDao utilisateurDao = DaoFactory.getUtilisateurDao();
}
