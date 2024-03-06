package fr.eni.projetEnchere.bll;

import java.util.List;

import fr.eni.projetEnchere.bo.ArticleVendu;
import fr.eni.projetEnchere.bo.Categorie;
import fr.eni.projetEnchere.bo.Utilisateur;
import fr.eni.projetEnchere.exceptions.UtilisateurNotFoundRuntimeException;

public interface EnchereService {
	// Méthode utilisateur
	
		Utilisateur creerCompte (Utilisateur utilisateur);// throws UtilisateurNotFoundRuntimeException;// voir regrouper 
		
		void modifierCompte (Utilisateur utilisateur) throws UtilisateurNotFoundRuntimeException;// voir regrouper 
		
		void supprimerCompte (int noUtilisateur) throws UtilisateurNotFoundRuntimeException;
		
		Utilisateur consulterCompteParId (int noUtilisateur) throws UtilisateurNotFoundRuntimeException;
	
		public Utilisateur findUtilisateurByPseudoOuEmail(String identifiant) ;

		
		// Méthode article-enchère
		
		ArticleVendu ajouterArticleVendu(ArticleVendu articleVendu);
		
		//List<Enchere> consulterEncheres ();
		
		//void rechercherArticle (String nomArticle, Categorie categorieArticle);
		
		//String afficherDetailArticle (int noArticle);
		
		//void creerEnchere (ArticleVendu articleVendu);// voir regrouper 
		
		//void modifierEnchere (ArticleVendu articleVendu);// voir regrouper 
		
		//void annulerEnchere (ArticleVendu articleVendu) ;
		
		
		// Méthode catégorie

		List<Categorie> getAllCategories();
		
		
}
