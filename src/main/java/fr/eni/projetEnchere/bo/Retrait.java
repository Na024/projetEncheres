package fr.eni.projetEnchere.bo;

public class Retrait {
	//Attributs
		private String rue;
		private String codePostal;
		private String ville;
		private boolean articleVendu;
		
		//Construteurs
		public Retrait() {
		}

		public Retrait(String rue, String codePostal, String ville, boolean articleVendu) {
			super();
			this.rue = rue;
			this.codePostal = codePostal;
			this.ville = ville;
			this.articleVendu = articleVendu;
		}

		//Méthodes
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Retrait [rue=");
			builder.append(rue);
			builder.append(", codePostal=");
			builder.append(codePostal);
			builder.append(", ville=");
			builder.append(ville);
			builder.append(", articleVendu=");
			builder.append(articleVendu);
			builder.append("]");
			return builder.toString();
		}
		
		//Getters et Setters
		public String getRue() {
			return rue;
		}

		public void setRue(String rue) {
			this.rue = rue;
		}

		public String getCodePostal() {
			return codePostal;
		}

		public void setCodePostal(String codePostal) {
			this.codePostal = codePostal;
		}

		public String getVille() {
			return ville;
		}

		public void setVille(String ville) {
			this.ville = ville;
		}

		public boolean isArticleVendu() {
			return articleVendu;
		}

		public void setArticleVendu(boolean articleVendu) {
			this.articleVendu = articleVendu;
		}
		

}
