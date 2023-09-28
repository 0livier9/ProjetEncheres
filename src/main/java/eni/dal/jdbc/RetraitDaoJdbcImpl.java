
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

	private static final String SELECT_ALL = "";
	private static final String SAVE = "INSERT INTO RETRAITS (no_article , rue, code_postal, ville ) VALUES (?,?,?,?) ";
	private static final String DELETE_ONE = "DELETE RETRAITS WHERE id = ?";

	@Override
	public void save(Retrait retrait) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SAVE);) {
			
			pstmt.setString(1, retrait.getRue());
			pstmt.setString(2, retrait.getCode_postal());
			pstmt.setString(3, retrait.getVille());
		
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
