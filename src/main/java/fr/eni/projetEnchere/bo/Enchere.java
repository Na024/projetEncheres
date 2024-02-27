package fr.eni.projetEnchere.bo;

import java.time.LocalDate;

public class Enchere {
	//Attributs
		private LocalDate dateEnchere;
		private Integer montant_enchere;
		private ArticleVendu articleVendu;
		private Utilisateur utilisateur;
		
		
		//Constructeurs
		public Enchere() {
		}


		public Enchere(LocalDate dateEnchere, Integer montant_enchere, ArticleVendu articleVendu, Utilisateur utilisateur) {
			super();
			this.dateEnchere = dateEnchere;
			this.montant_enchere = montant_enchere;
			this.articleVendu = articleVendu;
			this.utilisateur = utilisateur;
		}
		
		//Méthodes
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Enchere [dateEnchere=");
			builder.append(dateEnchere);
			builder.append(", montant_enchere=");
			builder.append(montant_enchere);
			builder.append(", articleVendu=");
			builder.append(articleVendu);
			builder.append(", utilisateur=");
			builder.append(utilisateur);
			builder.append("]");
			return builder.toString();
		}

		
		//Getters et Setters
		public LocalDate getDateEnchere() {
			return dateEnchere;
		}


		public void setDateEnchere(LocalDate dateEnchere) {
			this.dateEnchere = dateEnchere;
		}


		public Integer getMontant_enchere() {
			return montant_enchere;
		}


		public void setMontant_enchere(Integer montant_enchere) {
			this.montant_enchere = montant_enchere;
		}


		public ArticleVendu getArticleVendu() {
			return articleVendu;
		}


		public void setArticleVendu(ArticleVendu articleVendu) {
			this.articleVendu = articleVendu;
		}


		public Utilisateur getUtilisateur() {
			return utilisateur;
		}


		public void setUtilisateur(Utilisateur utilisateur) {
			this.utilisateur = utilisateur;
		}	 
}
