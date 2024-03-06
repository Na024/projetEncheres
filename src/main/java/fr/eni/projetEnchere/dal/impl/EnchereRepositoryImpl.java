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
        if (articleVendu.getNoArticle()==null || articleVendu.getNoArticle()== 0) {
            // Ajout nouvel article
			System.out.println("H");

        String sql = "INSERT into ARTICLES_VENDUS (nom_article, description, date_debut_encheres,date_fin_encheres, prix_initial,prix_vente,no_utilisateur,no_categorie)"
                    +" values ( :nom_article, :description, :date_debut_encheres, :date_fin_encheres, :prix_initial, :prix_vente, :no_utilisateur, :no_categorie)";
        System.out.println("I");
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue ("nom_article",articleVendu.getNomArticle());
        parameterSource.addValue ("description",articleVendu.getDescription());
        parameterSource.addValue ("date_debut_encheres",articleVendu.getDateDebutEncheres());
        parameterSource.addValue ("date_fin_encheres",articleVendu.getDateFinEncheres());
        parameterSource.addValue ("prix_initial",articleVendu.getMiseAPrix());
        parameterSource.addValue ("prix_vente",articleVendu.getPrixVente());
        parameterSource.addValue ("no_utilisateur",articleVendu.getVendeur());
        parameterSource.addValue ("no_categorie",articleVendu.getCategorieArticle());
        
        //Le conteneur qui va recevoir la clé primaire
        //générée par la base de données
        System.out.println("J");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        namedParameterjdbcTemplate.update(sql, parameterSource, keyHolder, 
                new String[] {"no_article"});
        
        
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
