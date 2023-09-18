package eni.dal;
import eni.dal.jdbc.UtilisateurDaoJdbcImpl;


public class DaoFactory {

	private DaoFactory() {}
	
	public static ArticleDao getArticleDao() {
		//return new GameMockDaoImpl();
		return new ArticlesDaoJdbcImpl();
	} 
	
	public static UtilisateurDao getUtilisateurDao() {
		return new UtilisateurDaoJdbcImpl();
	}
	
//	public static ForgetPasswordDao getForgetPasswordDao() {
//		return new ForgetPasswordDaoJdbcImpl();
//	}
	
}
