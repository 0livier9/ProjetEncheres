package eni.dal;

import eni.dal.jdbc.ArticleVenduDaoJdbcImpl;
import eni.dal.jdbc.CategorieDaoJdbcImpl;
import eni.dal.jdbc.EnchereDaoJdbcImpl;
import eni.dal.jdbc.RetraitDaoJdbcImpl;
import eni.dal.jdbc.UtilisateurDaoJdbcImpl;
import eni.dal.UtilisateurDao;

public class DaoFactory {

	private DaoFactory() {
	}

	public static EnchereDao getEnchereDao() {

		return new EnchereDaoJdbcImpl();
	}

	public static UtilisateurDao getUtilisateurDao() {
		return new UtilisateurDaoJdbcImpl();
	}

	public static ArticleVenduDao getArticleVendueDao() {

		return new ArticleVenduDaoJdbcImpl();
	}

	public static CategorieDao getCategorieDao() {

		return new CategorieDaoJdbcImpl();
	}

	public static RetraitDao getRetraitDao() {

		return new RetraitDaoJdbcImpl();
	}
}
