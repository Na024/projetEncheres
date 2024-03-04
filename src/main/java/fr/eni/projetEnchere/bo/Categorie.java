package fr.eni.projetEnchere.bo;

import java.util.List;

public class Categorie {
	//Attributs
	private Integer noCategorie;
	private String libelle;
	private List<ArticleVendu>articles;
	
	//Constructeurs
	public Categorie() {
	}

	public Categorie(Integer noCategorie, String libelle, List<ArticleVendu> articles) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
		this.articles = articles;
	}

	
	//Méthodes
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Categorie [noCategorie=");
		builder.append(noCategorie);
		builder.append(", libelle=");
		builder.append(libelle);
		builder.append(", articles=");
		builder.append(articles);
		builder.append("]");
		return builder.toString();
	}

	
	//Getters et Setters
	public Integer getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(Integer noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<ArticleVendu> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleVendu> articles) {
		this.articles = articles;
	}
	
}
