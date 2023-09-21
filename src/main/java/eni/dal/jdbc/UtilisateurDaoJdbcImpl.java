package eni.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eni.bo.Utilisateur;
import eni.dal.UtilisateurDao;
import eni.dal.jdbc.exception.JDBCException;

public class UtilisateurDaoJdbcImpl implements UtilisateurDao {
	
	private static final String INSERT_USER = "INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UNIQUE_PSEUDO_CONSTRAINT = "UQ_PSEUDO_UTILISATEURS";
	private static final String UNIQUE_EMAIL_CONSTRAINT = "UQ_EMAIL_UTILISATEURS";
	private static final String SELECT_BY_EMAIL = "SELECT * FROM UTILISATEURS WHERE email = ?";
	private static final String SELECT_BY_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo = ?";
	private static final String SELECT_BY_MOTDEPASSE = "SELECT * FROM UTILISATEURS WHERE mot_de_passe = ?";
	private static final String SELECT_BY_ID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?";
	private static final String DELETE_USER = "DELETE UTILISATEURS WHERE pseudo = ?";
	private static final String UPDATE = "UPDATE UTILISATEURS SET pseudo=?,nom=?,prenom=?,email=?,telephone=?,rue=?,code_postal=?,ville=?,mot_de_passe=?,credit=?,administrateur=? WHERE no_utilisateur = ?";

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
			}
		}

	@Override
	public Utilisateur findByEmail(String pseudoOrEmail) {
		try(
				Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_BY_EMAIL);
					){

				pstmt.setString(1, pseudoOrEmail);				
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					return new Utilisateur(rs.getInt("no_utilisateur"),
							rs.getString("pseudo"), 
							rs.getString("nom"), 
							rs.getString("prenom"),
							rs.getString("email"),
							rs.getString("telephone"),
							rs.getString("rue"),
							rs.getString("code_postal"),
							rs.getString("ville"),
							rs.getString("mot_de_passe"),
							rs.getInt("credit"),
							rs.getBoolean("administrateur")
							);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return null;
	}

	@Override
	public Utilisateur findById(int noUtilisateur) {
		try(
				Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_BY_ID);
					){

				pstmt.setInt(1, noUtilisateur);				
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					return new Utilisateur(rs.getInt("no_utilisateur"),
							rs.getString("pseudo"), 
							rs.getString("nom"), 
							rs.getString("prenom"),
							rs.getString("email"),
							rs.getString("telephone"),
							rs.getString("rue"),
							rs.getString("code_postal"),
							rs.getString("ville"),
							rs.getString("mot_de_passe"),
							rs.getInt("credit"),
							rs.getBoolean("administrateur")
							);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	
	
	@Override
	public Utilisateur findByPseudo(String pseudoOrEmail) {
		try(
				Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_BY_PSEUDO);
					){

				pstmt.setString(1, pseudoOrEmail);				
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					return new Utilisateur(rs.getInt("no_utilisateur"),
							rs.getString("pseudo"), 
							rs.getString("nom"), 
							rs.getString("prenom"),
							rs.getString("email"),
							rs.getString("telephone"),
							rs.getString("rue"),
							rs.getString("code_postal"),
							rs.getString("ville"),
							rs.getString("mot_de_passe"),
							rs.getInt("credit"),
							rs.getBoolean("administrateur")
							);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return null;
	}

	@Override
	public Utilisateur findByMotDePasse(String motDePasse) {
		try(
				Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_BY_EMAIL);
					){

				pstmt.setString(1, motDePasse);				
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					return new Utilisateur(rs.getInt("no_utilisateur"),
							rs.getString("pseudo"), 
							rs.getString("nom"), 
							rs.getString("prenom"),
							rs.getString("email"),
							rs.getString("telephone"),
							rs.getString("rue"),
							rs.getString("code_postal"),
							rs.getString("ville"),
							rs.getString("mot_de_passe"),
							rs.getInt("credit"),
							rs.getBoolean("administrateur")
							);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	
	
	@Override
	public void remove(String pseudo) {
		try (
				Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(DELETE_USER);	
			) {
			pstmt.setString(1, pseudo);
			pstmt.executeUpdate();
		}catch( SQLException e ) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void modify(Utilisateur utilisateur) {
		try(
				Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(UPDATE)
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
			
			pstmt.setInt(12, utilisateur.getNoUtilisateur());
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
