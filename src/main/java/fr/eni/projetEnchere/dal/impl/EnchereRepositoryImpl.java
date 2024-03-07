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
import fr.eni.projetEnchere.dal.EnchereRepository;

@Repository
public class EnchereRepositoryImpl implements EnchereRepository {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public EnchereRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
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