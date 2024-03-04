package fr.eni.projetEnchere.dal.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

	/*
	 * @Override public List<Categorie> getAllCategories() { String sql =
	 * "SELECT no_categorie, libelle FROM CATEGORIES"; return
	 * jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Categorie.class)); }
	 */

}
