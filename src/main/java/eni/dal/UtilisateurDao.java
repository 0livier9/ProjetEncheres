package eni.dal;

import java.rmi.ServerException;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import eni.bo.Utilisateur;
import eni.dal.jdbc.exception.JDBCException;

public interface UtilisateurDao {

	void save(Utilisateur utilisateur) throws JDBCException, SQLServerException;

	Utilisateur findByEmail(String email);

	Utilisateur findByPseudo(String pseudoOrEmail);

	Utilisateur findByMotDePasse(String motDePasse);

	Utilisateur findById(int noUtilisateur);

	void remove(String pseudo);

	void modify(Utilisateur utilisateur, String nouveauMotDePasse);

	void modifyCredit(int nouveauCredit, int noUtilisateur);
}
