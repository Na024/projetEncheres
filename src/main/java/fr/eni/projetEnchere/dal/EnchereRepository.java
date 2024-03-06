package fr.eni.projetEnchere.dal;

import fr.eni.projetEnchere.bo.ArticleVendu;
import fr.eni.projetEnchere.exceptions.ArticleNotFoundRuntimeException;

public interface EnchereRepository {

	ArticleVendu saveArticleVendu(ArticleVendu articleVendu) throws ArticleNotFoundRuntimeException;

	void supprimerArticleVendu(int noArticle) throws ArticleNotFoundRuntimeException;

}
