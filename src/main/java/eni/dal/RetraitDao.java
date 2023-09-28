package eni.dal;

import java.util.List;

import eni.bo.ArticleVendu;
import eni.bo.Categorie;
import eni.bo.Enchere;
import eni.bo.Retrait;

public interface RetraitDao {

	void save(Retrait retrait);

	List<Retrait> findAll();

	void remove(int id);

}
