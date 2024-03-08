package fr.eni.projetEnchere.dal.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.projetEnchere.bo.Categorie;
import fr.eni.projetEnchere.dal.CategorieRepository;

@Repository
public class CategorieRepositoryImpl implements CategorieRepository {

	private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CategorieRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

	@Override
	public List<Categorie> a() {
		String sql = "SELECT no_categorie, libelle FROM CATEGORIES";
		return jdbcTemplate.query(sql, new CategorieRowMapper() );
	}
	
	
	class CategorieRowMapper implements RowMapper<Categorie>{

		@Override
		public Categorie mapRow(ResultSet rs, int rowNum) throws SQLException {
			Categorie categorie = new Categorie();
			categorie.setNoCategorie(rs.getInt("no_categorie"));
			categorie.setLibelle(rs.getString("libelle"));
			return categorie;
		}
		
	}


	@Override
	public void consulterCategorie(int noCategorie) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Categorie> consulterCategorieParId(int noCategorie) {
		String sql = "SELECT no_categorie, libelle FROM CATEGORIES WHERE no_categorie = :noCategorie";
		Optional<Categorie> optCategorie = null;
		
		
		
		try {
			Categorie categorie = namedParameterJdbcTemplate.queryForObject(sql,
					new MapSqlParameterSource("noCategorie", noCategorie),
					new BeanPropertyRowMapper<>(Categorie.class));
			optCategorie = Optional.of(categorie);
		} catch (EmptyResultDataAccessException exc) {
			optCategorie = Optional.empty();
		}
		
		
		return optCategorie;
		
	}


}
