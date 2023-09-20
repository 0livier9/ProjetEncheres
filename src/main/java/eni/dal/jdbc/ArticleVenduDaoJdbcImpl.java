
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
import eni.bo.Utilisateur;
import eni.dal.EnchereDao;

public class ArticleVenduDaoJdbcImpl implements ArticleVenduDao {

	// Requetes SQL
	private static final String SELECT_ALL = "";
	private static final String SELECT_ONE = "SELECT * FROM ARTICLE_VENDUS WHERE no_article = ?";
	private static final String SAVE = "INSERT INTO ARTICLES_VENDUS " +
            "(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE_ONE = "DELETE ENCHERES WHERE id = ?";
	private static final String UPDATE = "UPDATE ARTICLES_VENDUS SET no_utilisateur=?,no_article=?,date_enchere=?,montant_enchere=? WHERE id = ?";
	private static final String FIND_BY_NAME = "SELECT * FROM ENCHERES WHERE no_article LIKE ? ";

	@Override
	public void save(ArticleVendu article) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SAVE);) {
			// valoriser les params de la requete
			pstmt.setString(1, article.getNomArticle());
			pstmt.setString(2, article.getDescription());
			pstmt.setInt(3, article.getPrixInitial());
			pstmt.setDate(6,Date.valueOf(article.getDateDebutEncheres()));
			pstmt.setDate(7, Date.valueOf(article.getDateFinEncheres()));
			pstmt.setInt(8, article.getNoUtilisateur());
			pstmt.setInt(9, article.getNoCategorie());
			pstmt.setString(10, article.getEtatVente());

		
			// executer la requete
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArticleVendu findOne(int id) {
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
	public List<ArticleVendu> findAll() {
		try (Connection connection = ConnectionProvider.getConnection();
				Statement stmt = connection.createStatement();) {
			List<ArticleVendu> articles = new ArrayList<ArticleVendu>();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while (rs.next()) {

				ArticleVendu article = new ArticleVendu(rs.getString("nom_article"), rs.getString("description"),
						rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"),
						rs.getInt("prix_vente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie"),
						rs.getString("etat_vente"));
				
				articles.add(article);

			}
			return articles;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void modify(ArticleVendu enchere) {
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
	public List<ArticleVendu> findByName(String query) {
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
