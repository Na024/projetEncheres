package fr.eni.projetEnchere.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import fr.eni.projetEnchere.bo.ArticleVendu;
import fr.eni.projetEnchere.bo.Categorie;

public interface EnchereRepository {
	

	//Optional<ArticleVendu> findArticleById(Integer noArticle);



	List<ArticleVendu> findArticleByMotCleAndCategorie(String motCle, int noCategorie);
}
