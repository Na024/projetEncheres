package fr.eni.projetEnchere.dal.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Cache.Connection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import fr.eni.projetEnchere.bo.ArticleVendu;
import fr.eni.projetEnchere.dal.EnchereRepository;
import fr.eni.projetEnchere.exceptions.ArticleNotFoundRuntimeException;

@Repository
public class EnchereRepositoryImpl implements EnchereRepository{


	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterjdbcTemplate;
	
	
	
	
	public EnchereRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterjdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterjdbcTemplate = namedParameterjdbcTemplate;
	}




	@Override
    public ArticleVendu saveArticleVendu(ArticleVendu articleVendu) throws ArticleNotFoundRuntimeException {
		if (articleVendu.getVendeur() == null) {
	        throw new IllegalArgumentException("Le vendeur de l'article doit être spécifié.");
	    }
		
		if (articleVendu.getNoArticle()==null || articleVendu.getNoArticle()== 0) {
            // Ajout nouvel article

        String sql = "INSERT into ARTICLES_VENDUS (nom_article, description, date_debut_encheres,date_fin_encheres, prix_initial,prix_vente,no_utilisateur,no_categorie)"
                    +" values ( :nom_article, :description, :date_debut_encheres, :date_fin_encheres, :prix_initial, :prix_vente, :no_utilisateur, :no_categorie)";
        System.out.println("Generated SQL: " + sql);

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue ("nom_article",articleVendu.getNomArticle());
        parameterSource.addValue ("description",articleVendu.getDescription());
        parameterSource.addValue ("date_debut_encheres",articleVendu.getDateDebutEncheres());
        parameterSource.addValue ("date_fin_encheres",articleVendu.getDateFinEncheres());
        parameterSource.addValue ("prix_initial",articleVendu.getMiseAPrix());
        parameterSource.addValue ("prix_vente",articleVendu.getPrixVente());
        parameterSource.addValue ("no_utilisateur",articleVendu.getVendeur().getNoUtilisateur());
        parameterSource.addValue ("no_categorie",articleVendu.getCategorieArticle());
        System.out.println("Article : " + articleVendu);
        System.out.println("Vendeur : " + articleVendu.getAcheteur() );
        //Le conteneur qui va recevoir la clé primaire
        //générée par la base de données
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        namedParameterjdbcTemplate.update(sql, parameterSource, keyHolder);

        
        
        articleVendu.setNoArticle(keyHolder.getKey().intValue());
        System.out.println("if");
        }else {
            String sql = "update ARTICLES_VENDUS set nom_article = ?, description = ?, date_debut_encheres = ?,date_fin_encheres = ?, prix_initial = ?,prix_vente = ?,no_utilisateur = ?,no_categorie = ?";
            int nbLignes = jdbcTemplate.update(sql,articleVendu.getNomArticle(), articleVendu.getDescription(),articleVendu.getDateDebutEncheres(),articleVendu.getDateFinEncheres(),articleVendu.getMiseAPrix(),articleVendu.getPrixVente(),articleVendu.getVendeur(),articleVendu.getCategorieArticle() );
            if (nbLignes==0) {
                throw new ArticleNotFoundRuntimeException();
            }
            System.out.println("else");
        }
        
        return articleVendu;
    }
	
	
	
	@Override
	public void supprimerArticleVendu(int noArticle) throws ArticleNotFoundRuntimeException {
		String sql ="delete from article_vendus where no_article=?";
		int nbLignes = jdbcTemplate.update(sql, noArticle);
		if(nbLignes == 0);{
			throw new ArticleNotFoundRuntimeException();
		}
		
	}



	
	
	
	
}
