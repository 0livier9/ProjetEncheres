
package eni.dal.jdbc;

import eni.dal.jdbc.*;
import eni.dal.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eni.bo.ArticleVendu;
import eni.bo.Categorie;
import eni.bo.Enchere;
import eni.bo.Retrait;
import eni.bo.Utilisateur;
import eni.dal.EnchereDao;

public class RetraitDaoJdbcImpl implements RetraitDao {

	// Requetes SQL
	private static final String SELECT_ALL = "";
	private static final String SELECT_ONE = "SELECT * FROM RETRAITS WHERE no_article = ?";
	private static final String SAVE = "INSERT INTO RETRAITS (no_article , rue, code_postal, ville ) VALUES (?,?,?,?) ";

	private static final String DELETE_ONE = "DELETE RETRAITS WHERE id = ?";
	private static final String UPDATE = "UPDATE ARTICLES_VENDUS SET no_utilisateur=?,no_article=?,date_enchere=?,montant_enchere=? WHERE id = ?";
	private static final String FIND_BY_NAME = "SELECT * FROM ENCHERES WHERE no_article LIKE ? ";

	@Override
	public void save(Retrait retrait) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SAVE);) {
			// valoriser les params de la requete
			pstmt.setString(1, retrait.getRue());
			pstmt.setString(2, retrait.getCode_postal());
			pstmt.setString(3, retrait.getVille());
		
		
			// executer la requete
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Retrait findOne(int id) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_ONE);) {
//			pstmt.setInt(1, id);			
//			ResultSet rs =  pstmt.executeQuery();
//			if(rs.next()) {
//					return new ArticleVendu(rs.getString("noArticle"), FIND_BY_NAME, DELETE_ONE, null, null, id, id, null)				
//			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Retrait> findAll() {
		try (Connection connection = ConnectionProvider.getConnection();
				Statement stmt = connection.createStatement();) {
			List<Retrait> retraits = new ArrayList<Retrait>();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while (rs.next()) {

				Retrait retrait = new Retrait(null, null, null);
				
				retraits.add(retrait);

			}
			return retraits;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void modify(Retrait categorie) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(UPDATE)) {

			// Update Set
//			pstmt.setString(1, game.getName());
//			pstmt.setString(2, game.getCompany());
//			pstmt.setString(3, game.getCategory());
//			pstmt.setFloat(4, game.getPrice());
//			pstmt.setDate(5, Date.valueOf(game.getReleaseDate()));
//			pstmt.setInt(6, game.getAge());
//			pstmt.setString(7, game.getFormat());
//			pstmt.setString(8, game.getVersion());
//			// Where id
//			pstmt.setInt(9, game.getId());
//			// execute
//			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove(int id) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(DELETE_ONE);) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Retrait> findByName(String query) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(FIND_BY_NAME)) {
////			pstmt.setString(1, "%" + query + "%");
////			List<ArticleVendu> games = new ArrayList<GaArticleVendume>();
////			ResultSet rs = pstmt.executeQuery();
////			while (rs.next()) {
////				games.add(
////
////						new ArticleVendu(rs.getInt("id"), rs.getString("name"), rs.getString("company"),
////								rs.getString("category"), rs.getFloat("price"), rs.getDate("releaseDate").toLocalDate(),
////								rs.getInt("age"), rs.getString("format"), rs.getString("version"))
//
//				);
//			}
//			return games;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
