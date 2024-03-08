package fr.eni.projetEnchere.dal;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import fr.eni.projetEnchere.bo.Categorie;

import fr.eni.projetEnchere.bo.ArticleVendu;
import fr.eni.projetEnchere.exceptions.ArticleNotFoundRuntimeException;

public interface EnchereRepository {
	

	//Optional<ArticleVendu> findArticleById(Integer noArticle);

	ArticleVendu saveArticleVendu(ArticleVendu articleVendu) throws ArticleNotFoundRuntimeException;

	void supprimerArticleVendu(int noArticle) throws ArticleNotFoundRuntimeException;

	List<ArticleVendu> findArticleByMotCleAndCategorie(String motCle, int noCategorie);
}
