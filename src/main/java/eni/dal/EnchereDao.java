package eni.dal;

import java.util.List;

import eni.bo.ArticleVendu;
import eni.bo.Enchere;

public interface EnchereDao {
	// CRUD
	void save(Enchere enchere);
	Enchere findOne(int noArticle);
	List<Enchere> findAll();
	void remove(int id);
	List<Enchere> findByName(String query);
	void modify(Enchere enchere);
	
}
