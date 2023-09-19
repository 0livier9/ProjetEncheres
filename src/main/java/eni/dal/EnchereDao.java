package eni.dal;

import java.util.List;

import eni.bo.ArticleVendu;
import eni.bo.Enchere;

public interface EnchereDao {
	// CRUD
	void save(Enchere enchere);
	Enchere findOne(int id);
	List<Enchere> findAll();
	void modify(Enchere enchere);
	void remove(int id);
	List<Enchere> findByName(String query);
}
