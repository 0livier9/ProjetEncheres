package eni.dal;

import eni.bo.Utilisateur;
import eni.dal.jdbc.exception.JDBCException;

public interface UtilisateurDao {

	void save(Utilisateur utilisateur) throws JDBCException;
	Utilisateur findByEmail(String email);
	Utilisateur findByPseudo(String pseudoOrEmail);
}
