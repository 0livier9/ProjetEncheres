package eni.bo;

import java.time.LocalDate;

public class Enchere {
	
	private ArticleVendu article;
	private Utilisateur user;
	private LocalDate dateEnchere;
	private int montantEnchere;
	private int noUser = user.getNoUtilisateur();
	private int noArticle = article.getNoArticle();
	
	
	public Enchere(int noUser,int noArticle ,LocalDate dateEnchere, int montantEnchere 
			) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.noUser = noUser;
		this.noArticle = noArticle;
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


	public int getNoUser() {
		return noUser;
	}


	public void setNoUser(int noUser) {
		this.noUser = noUser;
	}


	public int getNoArticle() {
		return noArticle;
	}


	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}


	
	

	
}
