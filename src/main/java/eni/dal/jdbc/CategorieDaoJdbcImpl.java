
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

public class CategorieDaoJdbcImpl implements CategorieDao {

	private static final String SELECT_ALL = "SELECT * FROM CATEGORIES";
	private static final String SELECT_ONE = "SELECT * FROM CATEGORIES WHERE no_categorie = ?";
	private static final String SAVE = "INSERT INTO CATEGORIES( libelle ) VALUES (? )";
	private static final String DELETE_ONE = "DELETE ENCHERES WHERE no_categorie = ?";
	private static final String UPDATE = "UPDATE CATEGORIES SET libelle=? WHERE no_categorie = ?";

	@Override
	public void save(Categorie categorie) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SAVE);) {

			pstmt.setInt(1, categorie.getNoCategorie());
			pstmt.setString(2, categorie.getLibelle());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Categorie findOne(int no_categorie) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_ONE);) {
			pstmt.setInt(1, no_categorie);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Categorie(rs.getInt(no_categorie), rs.getString("libelle"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Categorie> findAll() {
		try (Connection connection = ConnectionProvider.getConnection();
				Statement stmt = connection.createStatement();) {
			List<Categorie> categories = new ArrayList<Categorie>();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while (rs.next()) {

				Categorie categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));

				categories.add(categorie);

			}
			return categories;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void modify(Categorie categorie) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(UPDATE)) {

			pstmt.setString(1, categorie.getLibelle());

			pstmt.setInt(2, categorie.getNoCategorie());

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
