package eni.dal;

import java.util.List;

import eni.bo.ArticleVendu;
import eni.bo.Categorie;
import eni.bo.Enchere;
import eni.bo.Retrait;

public interface RetraitDao {
	// CRUD
	void save(Retrait retrait);
	Retrait findOne(int id);
	List<Retrait> findAll();
	void modify(Retrait retrait);
	void remove(int id);
	List<Retrait> findByName(String query);
}
