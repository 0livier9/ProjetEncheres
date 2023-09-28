package eni.bll;

import java.util.List;

import eni.bll.exception.BLLException;
import eni.bo.ArticleVendu;
import eni.bo.Enchere;
import eni.dal.DaoFactory;
import eni.dal.EnchereDao;
import eni.dal.UtilisateurDao;
import jakarta.servlet.http.HttpSession;

public class EnchereManager {

	private static EnchereManager instance;

	private EnchereManager() {
	}

	public static EnchereManager getInstance() {
		if (instance == null)
			instance = new EnchereManager();
		return instance;
	}

	private EnchereDao enchereDao = DaoFactory.getEnchereDao();
	private UtilisateurDao utilisateurDao = DaoFactory.getUtilisateurDao();

	public List<Enchere> recupTousLesEncheres() {
		return enchereDao.findAll();
	}

	public void ajouterUneEnchere(Enchere enchere) throws BLLException {

		Enchere ancienneEnchere = enchereDao.findOne(enchere.getArticle().getNoArticle());

		if (ancienneEnchere == null) {
			if (enchere.getUser().getCredit() >= enchere.getMontantEnchere()) {
				enchereDao.save(enchere);
			} else {
				throw new BLLException("Vous n'avez pas assez de crédit ");
			}

		} else {
			if (ancienneEnchere.getUser().getNoUtilisateur() == enchere.getUser().getNoUtilisateur()) {
				throw new BLLException("Vous avez déjà la meilleur enchère");
			}

			if (enchere.getMontantEnchere() > ancienneEnchere.getMontantEnchere()
					&& enchere.getUser().getCredit() >= enchere.getMontantEnchere()) {
				int nouveauCredit = enchere.getUser().getCredit() - enchere.getMontantEnchere();
				utilisateurDao.modifyCredit(nouveauCredit, enchere.getUser().getNoUtilisateur());
				int majCredit = ancienneEnchere.getUser().getCredit() + ancienneEnchere.getMontantEnchere();
				utilisateurDao.modifyCredit(majCredit, ancienneEnchere.getUser().getNoUtilisateur());
				enchereDao.modify(enchere);

			} else {
				if (enchere.getMontantEnchere() <= ancienneEnchere.getMontantEnchere()) {
					throw new BLLException("Merci de mettre une enchère supérieure à la précédente ");
				}
				if (enchere.getUser().getCredit() < enchere.getMontantEnchere()) {
					throw new BLLException("Vous n'avez pas assez de crédit ");
				}
			}
		}
	}

	public Enchere trouverUneEnchere(int noArticle) {
		Enchere enchere = enchereDao.findOne(noArticle);
		return enchere;
	}

}
