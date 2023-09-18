package eni.dal;

import eni.bo.Utilisateur;
import eni.dal.jdbc.exception.JDBCException;

public interface UtilisateurDao {

	Utilisateur findByUsername(String username);
	void save(Utilisateur user) throws JDBCException;
	void update(Utilisateur user);
	Utilisateur findByEmail(String email);
}
