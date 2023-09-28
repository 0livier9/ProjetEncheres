package eni.bo;

import java.time.LocalDate;

public class Enchere {

	private ArticleVendu article;
	private Utilisateur user;

	private LocalDate dateEnchere;
	private int montantEnchere;

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public Enchere(Utilisateur user, ArticleVendu article, LocalDate dateEnchere, int montantEnchere) {
		this.user = user;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.article = article;
	}

	public Enchere(ArticleVendu article, Utilisateur user) {

		this.article = article;
		this.user = user;
	}

	public ArticleVendu getArticle() {
		return article;
	}

	public void setArticle(ArticleVendu article) {
		this.article = article;
	}

	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

}
