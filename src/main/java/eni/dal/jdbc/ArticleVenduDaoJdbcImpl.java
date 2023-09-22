
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
	private static final String SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS INNER JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur";
	private static final String SELECT_ONE = "SELECT * FROM ARTICLES_VENDUS WHERE no_article = ?";
	private static final String SAVE = "INSERT INTO ARTICLES_VENDUS " +
            "(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE_ONE = "DELETE ENCHERES WHERE id = ?";
	private static final String UPDATE = "UPDATE ARTICLES_VENDUS SET no_utilisateur=?,no_article=?,date_enchere=?,montant_enchere=? WHERE id = ?";
	private static final String FIND_BY_NAME = "SELECT * FROM ARTICLES_VENDUS WHERE nom_article LIKE ? ";

	@Override
	public void save(ArticleVendu article) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SAVE);) {
			// valoriser les params de la requete
			pstmt.setString(1, article.getNomArticle());
			pstmt.setString(2, article.getDescription());
			pstmt.setDate(3,Date.valueOf(article.getDateDebutEncheres()));
			pstmt.setDate(4, Date.valueOf(article.getDateFinEncheres()));
			pstmt.setInt(5, article.getPrixInitial());
			pstmt.setInt(6, article.getPrixVente());
			pstmt.setInt(7, article.getVendeur().getNoUtilisateur());
			pstmt.setInt(8, article.getVendeur().getNoUtilisateur());
			pstmt.setString(9, article.getEtatVente());

	
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
			pstmt.setInt(1, id);			
			ResultSet rs =  pstmt.executeQuery();
			if(rs.next()) {
				
				Categorie categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
				
				Utilisateur vendeur = new Utilisateur(rs.getInt("no_utilisateur"),
						rs.getString("pseudo"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("email"), 
						rs.getString("telephone"),
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville"),rs.getString("mot_de_passe"), 0, false);
				
				
					return  new ArticleVendu(rs.getInt("no_article"),rs.getString("nom_article"),
							rs.getString("description"),
							rs.getDate("date_debut_encheres").toLocalDate(), 
							rs.getDate("date_fin_encheres").toLocalDate(), 
							rs.getInt("prix_initial"),
							rs.getInt("prix_vente"), vendeur, categorie,
							rs.getString("etat_vente"));			
			}			
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
				
				Categorie categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
				
				Utilisateur vendeur = new Utilisateur(rs.getInt("no_utilisateur"),
						rs.getString("pseudo"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("email"), 
						rs.getString("telephone"),
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville"),rs.getString("mot_de_passe"), 0, false);

				ArticleVendu article = new ArticleVendu(rs.getString("nom_article"),
						rs.getString("description"),
						rs.getDate("date_debut_encheres").toLocalDate(), 
						rs.getDate("date_fin_encheres").toLocalDate(), 
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"), vendeur, categorie,
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
			pstmt.setString(1, "%" + query + "%");
			List<ArticleVendu> articles = new ArrayList<ArticleVendu>();
			
			ResultSet rs = pstmt.executeQuery();
		
			while (rs.next()) {
				Categorie categorie = new Categorie();
				
				Utilisateur vendeur = new Utilisateur(rs.getInt("no_utilisateur"),
						rs.getString("pseudo"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("email"), 
						rs.getString("telephone"),
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville"),rs.getString("mot_de_passe"), 0, false);
				articles.add(
						

						new ArticleVendu(rs.getString("nom_article"),
								rs.getString("description"),
								rs.getDate("date_debut_encheres").toLocalDate(), 
								rs.getDate("date_fin_encheres").toLocalDate(), 
								rs.getInt("prix_initial"),
								rs.getInt("prix_vente"), vendeur, categorie,
								rs.getString("etat_vente")));	
				
			}
			return articles;
		} 

		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
