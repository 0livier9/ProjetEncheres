package eni.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import eni.bo.Utilisateur;
import eni.dal.UtilisateurDao;
import eni.dal.jdbc.exception.JDBCException;

public class UtilisateurDaoJdbcImpl implements UtilisateurDao {
	
	private static final String INSERT_USER = "INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe) "
			+ "VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String UNIQUE_USERNAME_CONSTRAINT = "UQ_USERNAME_USERS";
	
	
	
	
	@Override
	public Utilisateur findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Utilisateur user) throws eni.dal.jdbc.exception.JDBCException {
		try(
				Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(INSERT_USER);
					){
				pstmt.setString(1, user.getPseudo());
				pstmt.setString(2, user.getNom());
				pstmt.setString(3, user.getPrenom());
				pstmt.setString(4, user.getEmail());
				pstmt.setString(5, user.getTelephone());
				pstmt.setString(6, user.getRue());
				pstmt.setString(7, user.getCodePostal());
				pstmt.setString(8, user.getVille());
				pstmt.setString(9, user.getMotDePasse());
				
				pstmt.executeUpdate();
			}catch(SQLException e) {
				if(e.getMessage().contains(UNIQUE_USERNAME_CONSTRAINT)) {
					throw new JDBCException("Le nom de l'utilisateur existe déja!");
				}
				e.printStackTrace();
			}
		}


	@Override
	public void update(Utilisateur user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Utilisateur findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
