package fr.eni.projetEnchere.dal.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.projetEnchere.bo.ArticleVendu;
import fr.eni.projetEnchere.bo.Categorie;
import fr.eni.projetEnchere.bo.Utilisateur;

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
public class EnchereRepositoryImpl implements EnchereRepository {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public EnchereRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}







	@Override
    public ArticleVendu saveArticleVendu(ArticleVendu articleVendu) throws ArticleNotFoundRuntimeException {
		if (articleVendu.getVendeur() == null) {
	        throw new IllegalArgumentException("Le vendeur de l'article doit être spécifié.");
	    }
		
		if (articleVendu.getNoArticle()==null || articleVendu.getNoArticle()== 0) {
            // Ajout nouvel article

        String sql = "INSERT into ARTICLES_VENDUS (nom_article, description, date_debut_encheres,date_fin_encheres, prix_initial,no_utilisateur,no_categorie)"
                    +" values ( :nom_article, :description, :date_debut_encheres, :date_fin_encheres, :prix_initial, :no_utilisateur, :no_categorie)";
        System.out.println("Generated SQL: " + sql);

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue ("nom_article",articleVendu.getNomArticle());
        parameterSource.addValue ("description",articleVendu.getDescription());
        parameterSource.addValue ("date_debut_encheres",articleVendu.getDateDebutEncheres());
        parameterSource.addValue ("date_fin_encheres",articleVendu.getDateFinEncheres());
        parameterSource.addValue ("prix_initial",articleVendu.getMiseAPrix());
        //parameterSource.addValue ("prix_vente",articleVendu.getPrixVente());
        parameterSource.addValue ("no_utilisateur",articleVendu.getVendeur().getNoUtilisateur());
        parameterSource.addValue ("no_categorie",articleVendu.getCategorieArticle().getNoCategorie());
        System.out.println("Article : " + articleVendu);
        System.out.println("Vendeur : " + articleVendu.getVendeur().getNoUtilisateur() );
        
        //Le conteneur qui va recevoir la clé primaire
        //générée par la base de données
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        namedParameterJdbcTemplate.update(sql, parameterSource, keyHolder);

        
        
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



	
	
	
	


	// filtre pour selectionner les articles par motCle et catégorie
	
		@Override
		public List<ArticleVendu> findArticleByMotCleAndCategorie(String motCle, int noCategorie) {
		String sql = "SELECT a.no_article, a.nom_article, a.prix_initial,a.date_fin_encheres, u.pseudo " 
				+ "FROM ARTICLES_VENDUS a "
				+ "INNER JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur "
				+ "INNER JOIN CATEGORIES c ON c.no_categorie = a.no_categorie "
				+ "WHERE a.nom_article LIKE :nom_article AND c.no_categorie = :no_categorie";
		
		
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("nom_article", "%" + motCle + "%");
			param.addValue("no_categorie", noCategorie);
			
			 try {
			        return namedParameterJdbcTemplate.query(sql, param, new ArticleVenduRowMapper());
			    } catch (EmptyResultDataAccessException exc) {
			        System.err.println(exc.getMessage());
			        exc.printStackTrace();
			        return Collections.emptyList(); // Retourner une liste vide en cas de résultat vide
			    }
			}

		
		class ArticleVenduRowMapper implements RowMapper<ArticleVendu>{

			@Override
			public ArticleVendu mapRow(ResultSet rs, int rowNum) throws SQLException {
				ArticleVendu articleVendu = new ArticleVendu();
				articleVendu.setNoArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setMiseAPrix(rs.getInt("prix_initial"));
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				Utilisateur vendeur = new Utilisateur();
				vendeur.setPseudo(rs.getString("pseudo"));
				articleVendu.setVendeur(vendeur);	
				return articleVendu;
			}
			
			
		}
		

	
//         // AFFICHER DETAIL ANNONCE
//		@Override
//		public Optional<ArticleVendu> findArticleById(Integer noArticle) {
//			String sql = "SELECT a.*, c.libelle,u.pseudo" 
//					+"FROM ARTICLES_VENDUS a "
//					+"INNER JOIN UTILISATEUR u ON a.no_utilisateur = u.no_utilisateur "
//					+"INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie"
//					+"WHERE a.no_article = ?";
//			
//			
//		
//			Optional<ArticleVendu> optArticleVendu = null;
//		
//			RowMapper<ArticleVendu> rowMapper = new RowMapper<ArticleVendu> (){
//		
//		
//				@Override
//				public ArticleVendu mapRow(ResultSet rs, int rowNum) throws SQLException {
//					ArticleVendu articleVendu = new ArticleVendu();
//					Utilisateur vendeur = new Utilisateur();
//					Categorie categorie = new Categorie();
//		
//					articleVendu.setNoArticle(rs.getInt("nom_article"));
//					articleVendu.setNomArticle(rs.getString("nom_article"));
//					articleVendu.setDescription(rs.getString("description"));
//					articleVendu.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
//					articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
//					articleVendu.setMiseAPrix(rs.getInt("prix_initial"));
//					articleVendu.setPrixVente(rs.getInt("prix_vente"));
//		
//					//set du vendeur
//					vendeur.setPseudo(rs.getString("pseudo"));
//					articleVendu.setVendeur (vendeur);
//		
//					//set de la categorie
//					categorie.setLibelle(rs.getString("libelle"));
//					articleVendu.setCategorieArticle(categorie);
//		
//					return articleVendu;
//					}
//				
//				};
//			
//				
//			}
//
//
//
//	
}