package fr.eni.projetEnchere.bo;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ArticleVendu {
	//Attribut
		private Integer noArticle;
		private String nomArticle;
		private String description;
		private LocalDate dateDebutEncheres;
		private LocalDate dateFinEncheres;
		private Integer miseAPrix;
		private Integer prixVente;
		private String etatVente;
		
		private Utilisateur acheteur;
		private Utilisateur vendeur;
		
		private Categorie categorieArticle;
		private Retrait lieuRetrait;
		private List<Enchere> enchères;
		
		//Constructeurs
		public ArticleVendu() {
		}

		public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
				Integer miseAPrix, Integer prixVente, String etatVente, Utilisateur acheteur, Utilisateur vendeur,
				Categorie categorieArticle, Retrait lieuRetrait, List<Enchere> enchères) {
			super();
			this.nomArticle = nomArticle;
			this.description = description;
			this.dateDebutEncheres = dateDebutEncheres;
			this.dateFinEncheres = dateFinEncheres;
			this.miseAPrix = miseAPrix;
			this.prixVente = prixVente;
			this.etatVente = etatVente;
			this.acheteur = acheteur;
			this.vendeur = vendeur;
			this.categorieArticle = categorieArticle;
			this.lieuRetrait = lieuRetrait;
			this.enchères = enchères;
		}

		public ArticleVendu(Integer noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
				LocalDate dateFinEncheres, Integer miseAPrix, Integer prixVente, String etatVente, Utilisateur acheteur,
				Utilisateur vendeur, Categorie categorieArticle, Retrait lieuRetrait, List<Enchere> enchères) {
			super();
			this.noArticle = noArticle;
			this.nomArticle = nomArticle;
			this.description = description;
			this.dateDebutEncheres = dateDebutEncheres;
			this.dateFinEncheres = dateFinEncheres;
			this.miseAPrix = miseAPrix;
			this.prixVente = prixVente;
			this.etatVente = etatVente;
			this.acheteur = acheteur;
			this.vendeur = vendeur;
			this.categorieArticle = categorieArticle;
			this.lieuRetrait = lieuRetrait;
			this.enchères = enchères;
		}

		//Méthodes
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("ArticleVendu [noArticle=");
			builder.append(noArticle);
			builder.append(", nomArticle=");
			builder.append(nomArticle);
			builder.append(", description=");
			builder.append(description);
			builder.append(", dateDebutEncheres=");
			builder.append(dateDebutEncheres);
			builder.append(", dateFinEncheres=");
			builder.append(dateFinEncheres);
			builder.append(", miseAPrix=");
			builder.append(miseAPrix);
			builder.append(", prixVente=");
			builder.append(prixVente);
			builder.append(", etatVente=");
			builder.append(etatVente);
			builder.append(", acheteur=");
			builder.append(acheteur);
			builder.append(", vendeur=");
			builder.append(vendeur);
			builder.append(", categorieArticle=");
			builder.append(categorieArticle);
			builder.append(", lieuRetrait=");
			builder.append(lieuRetrait);
			builder.append(", enchères=");
			builder.append(enchères);
			builder.append("]");
			return builder.toString();
		}

		@Override
		public int hashCode() {
			return Objects.hash(noArticle);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ArticleVendu other = (ArticleVendu) obj;
			return Objects.equals(noArticle, other.noArticle);
		}

		
		//Getters et Setters
		public Integer getNoArticle() {
			return noArticle;
		}

		public void setNoArticle(Integer noArticle) {
			this.noArticle = noArticle;
		}

		public String getNomArticle() {
			return nomArticle;
		}

		public void setNomArticle(String nomArticle) {
			this.nomArticle = nomArticle;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public LocalDate getDateDebutEncheres() {
			return dateDebutEncheres;
		}

		public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
			this.dateDebutEncheres = dateDebutEncheres;
		}

		public LocalDate getDateFinEncheres() {
			return dateFinEncheres;
		}

		public void setDateFinEncheres(LocalDate dateFinEncheres) {
			this.dateFinEncheres = dateFinEncheres;
		}

		public Integer getMiseAPrix() {
			return miseAPrix;
		}

		public void setMiseAPrix(Integer miseAPrix) {
			this.miseAPrix = miseAPrix;
		}

		public Integer getPrixVente() {
			return prixVente;
		}

		public void setPrixVente(Integer prixVente) {
			this.prixVente = prixVente;
		}

		public String getEtatVente() {
			return etatVente;
		}

		public void setEtatVente(String etatVente) {
			this.etatVente = etatVente;
		}

		public Utilisateur getAcheteur() {
			return acheteur;
		}

		public void setAcheteur(Utilisateur acheteur) {
			this.acheteur = acheteur;
		}

		public Utilisateur getVendeur() {
			return vendeur;
		}

		public void setVendeur(Utilisateur vendeur) {
			this.vendeur = vendeur;
		}

		public Categorie getCategorieArticle() {
			return categorieArticle;
		}

		public void setCategorieArticle(Categorie categorieArticle) {
			this.categorieArticle = categorieArticle;
		}

		public Retrait getLieuRetrait() {
			return lieuRetrait;
		}

		public void setLieuRetrait(Retrait lieuRetrait) {
			this.lieuRetrait = lieuRetrait;
		}

		public List<Enchere> getEnchères() {
			return enchères;
		}

		public void setEnchères(List<Enchere> enchères) {
			this.enchères = enchères;
		}

}
