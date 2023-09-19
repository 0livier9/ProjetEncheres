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
import eni.bo.Enchere;

import eni.dal.EnchereDao;

public class EnchereDaoJdbcImpl implements EnchereDao {

	// Requetes SQL
	private static final String SELECT_ALL = "SELECT * FROM ENCHERES";
	private static final String SELECT_ONE = "SELECT * FROM ENCHERES WHERE id = ?";
	private static final String SAVE = "INSERT ARTICLES_VENDUS (no_utilisateur,no_article,date_enchere,montant_enchere) VALUES (?,?,?,?,?)";
	private static final String DELETE_ONE = "DELETE ENCHERES WHERE id = ?";
	private static final String UPDATE = "UPDATE ARTICLES_VENDUS SET no_utilisateur=?,no_article=?,date_enchere=?,montant_enchere=? WHERE id = ?";
	private static final String FIND_BY_NAME = "SELECT * FROM ENCHERES WHERE no_article LIKE ? ";

	@Override
	public void save(Enchere enchere) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SAVE);) {
			// valoriser les params de la requete
			pstmt.setString(1, enchere.getArticle().toString());
			pstmt.setDate(3, Date.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(5, (enchere.getMontantEnchere()));

			// executer la requete
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Enchere findOne(int id) {
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
	public List<Enchere> findAll() {
		try (Connection connection = ConnectionProvider.getConnection();
				Statement stmt = connection.createStatement();) {
			List<Enchere> encheres = new ArrayList<Enchere>();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while (rs.next()) {
				encheres.add(

						new Enchere(rs.getInt("no_utilisateur"), rs.getInt("no_article"),
								rs.getDate("date_enchere").toLocalDate(), rs.getInt("montant_enchere")

						));
			}
			return encheres;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void modify(Enchere enchere) {
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
	public List<Enchere> findByName(String query) {
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
