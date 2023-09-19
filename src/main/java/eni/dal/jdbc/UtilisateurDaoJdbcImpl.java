package eni.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import eni.bo.Utilisateur;
import eni.dal.UtilisateurDao;
import eni.dal.jdbc.exception.JDBCException;

public class UtilisateurDaoJdbcImpl implements UtilisateurDao {
	
	private static final String INSERT_USER = "INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UNIQUE_PSEUDO_CONSTRAINT = "UQ_PSEUDO_UTILISATEURS";
	private static final String UNIQUE_EMAIL_CONSTRAINT = "UQ_EMAIL_UTILISATEURS";
	
	
	
	
	@Override
	public Utilisateur findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Utilisateur utilisateur) throws JDBCException {
		try(
				Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(INSERT_USER);
					){
			
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
				if(e.getMessage().contains(UNIQUE_PSEUDO_CONSTRAINT)) {
					throw new JDBCException("Ce pseudo existe déja!");
				}
				if(e.getMessage().contains(UNIQUE_EMAIL_CONSTRAINT)) {
					throw new JDBCException("Ce mail existe déja!");
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
