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

	private static final String SELECT_ALL = "SELECT * FROM ENCHERES INNER JOIN ARTICLES_VENDUS ON ENCHERES.no_article = ARTICLES_VENDUS.no_article \r\n"
			+ "INNER JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie INNER JOIN  UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur;";
	private static final String SELECT_ONE = "SELECT * FROM ENCHERES INNER JOIN ARTICLES_VENDUS ON ENCHERES.no_article = ARTICLES_VENDUS.no_article \r\n"
			+ "INNER JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie INNER JOIN  UTILISATEURS ON ENCHERES.no_utilisateur=UTILISATEURS.no_utilisateur \r\n"
			+ "WHERE ENCHERES.no_article=?";
	private static final String SAVE = "INSERT INTO ENCHERES (no_utilisateur,no_article,date_enchere,montant_enchere) VALUES (?,?,?,?)";
	private static final String DELETE_ONE = "DELETE ENCHERES WHERE id = ?";
	private static final String UPDATE = "UPDATE ENCHERES SET no_utilisateur=?,date_enchere=?,montant_enchere=? WHERE no_article = ?";

	@Override
	public void save(Enchere enchere) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SAVE);) {

			pstmt.setInt(1, enchere.getUser().getNoUtilisateur());
			pstmt.setInt(2, enchere.getArticle().getNoArticle());
			pstmt.setDate(3, Date.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(4, enchere.getMontantEnchere());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Enchere findOne(int noArticle) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_ONE);) {
			pstmt.setInt(1, noArticle);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Utilisateur user = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"),
						rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"),
						rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"),
						rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));

				Categorie categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));

				ArticleVendu article = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description"), rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"), 0, user, categorie,
						rs.getString("etat_vente"));

				Enchere encheres = new Enchere(user, article, rs.getDate("date_enchere").toLocalDate(),
						rs.getInt("montant_enchere"));
				return encheres;
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
				Utilisateur user = new Utilisateur(rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"));

				Categorie categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));

				ArticleVendu article = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description"), rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"), 0, user, categorie,
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

			pstmt.setInt(1, enchere.getUser().getNoUtilisateur());
			pstmt.setDate(2, Date.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(3, enchere.getMontantEnchere());
			pstmt.setInt(4, enchere.getArticle().getNoArticle());

			pstmt.executeUpdate();

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
}
