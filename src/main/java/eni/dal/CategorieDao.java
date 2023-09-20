package eni.dal;

import java.util.List;

import eni.bo.ArticleVendu;
import eni.bo.Categorie;
import eni.bo.Enchere;

public interface CategorieDao {
	// CRUD
	void save(Categorie categorie);
	Categorie findOne(int id);
	List<Categorie> findAll();
	void modify(Categorie categorie);
	void remove(int id);
	List<Categorie> findByName(String query);
}
