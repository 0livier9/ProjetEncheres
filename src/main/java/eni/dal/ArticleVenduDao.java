package eni.dal;

import java.util.List;

import eni.bo.ArticleVendu;
import eni.bo.Enchere;

public interface ArticleVenduDao {
	// CRUD
	void save(ArticleVendu article);
	ArticleVendu findOne(int id);
	List<ArticleVendu> findAll();
	void modify(ArticleVendu article);
	void remove(int id);
	List<ArticleVendu> findByName(String query);
	List<ArticleVendu> findByCat(int query);
}
