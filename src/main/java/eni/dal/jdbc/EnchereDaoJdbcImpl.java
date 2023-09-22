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

public class EnchereDaoJdbcImpl implements EnchereDao {

	// Requetes SQL
	private static final String SELECT_ALL = "SELECT * FROM ENCHERES INNER JOIN ARTICLES_VENDUS ON ENCHERES.no_article = ARTICLES_VENDUS.no_article \r\n"
			+ "INNER JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie INNER JOIN  UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur;";
	private static final String SELECT_ONE = "SELECT * FROM ENCHERES WHERE no_utilisateur = ? AND no_article=?";
	private static final String SAVE = "";
	private static final String DELETE_ONE = "DELETE ENCHERES WHERE id = ?";
	private static final String UPDATE = "UPDATE ARTICLES_VENDUS SET no_utilisateur=?,no_article=?,date_enchere=?,montant_enchere=? WHERE id = ?";
	private static final String FIND_BY_NAME = "SELECT * FROM ENCHERES WHERE no_article LIKE ? ";

	@Override
	public void save(Enchere enchere) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SAVE);) {

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
			pstmt.setInt(1, id);			
			ResultSet rs =  pstmt.executeQuery();
			if(rs.next()) {
					return new Enchere(null, null);				
			}			
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
				Utilisateur user = new Utilisateur(rs.getString("pseudo"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("email"),
						rs.getString("telephone"),
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville"),
						rs.getString("mot_de_passe"));
				
				Categorie categorie = new Categorie(rs.getInt("no_categorie"),
						rs.getString("libelle"));
				
				ArticleVendu article = new ArticleVendu(rs.getInt("no_article"),
						rs.getString("nom_article"), rs.getString("description"),
						rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(),
						rs.getInt("prix_initial"), 0,
						user,
						categorie,
						rs.getString("etat_vente"));
			
			
				encheres.add(

						new Enchere(user, article, rs.getDate("date_enchere").toLocalDate(),
								rs.getInt("montant_enchere")

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
