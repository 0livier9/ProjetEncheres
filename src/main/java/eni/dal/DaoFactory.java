package eni.dal;

import eni.dal.jdbc.EnchereDaoJdbcImpl;
import eni.dal.jdbc.UtilisateurDaoJdbcImpl;
import eni.dal.UtilisateurDao;



public class DaoFactory {

	private DaoFactory() {}
	
	public static EnchereDao getEnchereDao() {
		//return new GameMockDaoImpl();
		return new EnchereDaoJdbcImpl();
	} 
	
	public static UtilisateurDao getUtilisateurDao() {
		return new UtilisateurDaoJdbcImpl();
	}
	
//	public static ForgetPasswordDao getForgetPasswordDao() {
//		return new ForgetPasswordDaoJdbcImpl();
//	}
	
}
