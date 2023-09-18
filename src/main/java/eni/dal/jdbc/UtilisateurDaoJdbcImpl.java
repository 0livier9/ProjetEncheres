package eni.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import eni.bo.Utilisateur;
import eni.dal.UtilisateurDao;
import eni.dal.jdbc.exception.JDBCException;

public class UtilisateurDaoJdbcImpl implements UtilisateurDao {
	
	private static final String INSERT_USER = "INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UNIQUE_USERNAME_CONSTRAINT = "UQ_USERNAME_USERS";
	
	
	
	
	@Override
	public Utilisateur findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Utilisateur utilisateur) throws eni.dal.jdbc.exception.JDBCException {
		try(
				Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(INSERT_USER);
					){
			
				System.out.println(utilisateur.getNom());
				System.out.println(utilisateur.getCredit());
				
			
				pstmt.setString(1, utilisateur.getPseudo());
				pstmt.setString(2, utilisateur.getNom());
				pstmt.setString(3, utilisateur.getPrenom());
				pstmt.setString(4, utilisateur.getEmail());
				pstmt.setString(5, utilisateur.getTelephone());
				pstmt.setString(6, utilisateur.getRue());
				pstmt.setString(7, utilisateur.getCodePostal());
				pstmt.setString(8, utilisateur.getVille());
				pstmt.setString(9, utilisateur.getMotDePasse());
				pstmt.setInt(10, utilisateur.getCredit());
				pstmt.setBoolean(11, utilisateur.isAdministrateur());
				
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
