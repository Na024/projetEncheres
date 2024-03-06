package fr.eni.projetEnchere.dal.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import fr.eni.projetEnchere.bo.Utilisateur;
import fr.eni.projetEnchere.dal.UtilisateurRepository;
import fr.eni.projetEnchere.exceptions.UtilisateurNotFoundRuntimeException;
@Repository
public class UtilisateurRepositoryImpl implements UtilisateurRepository {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterjdbcTemplate;
	private BCryptPasswordEncoder passwordEncoder;
	
	public UtilisateurRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterjdbcTemplate,
			BCryptPasswordEncoder passwordEncoder) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterjdbcTemplate = namedParameterjdbcTemplate;
		this.passwordEncoder = passwordEncoder;
	}




		@Override
		public Optional<Utilisateur> findUtilisateurByPseudoOuEmail(String identifiant) {
			System.out.println("aaaa (utilisateurRepository)");
			System.err.println(identifiant);
			System.out.println("bbbb (utilisateurRepository)");
	    String sql = "SELECT * FROM utilisateurs WHERE pseudo = :identifiant";
	    
	    Optional<Utilisateur> optUtilisateur = null;
	    
	    try {
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("identifiant",identifiant);	
			Utilisateur utilisateur = namedParameterjdbcTemplate.queryForObject(
					sql, 
					param, 
					new UserRowMapper()
					);
			System.err.println(utilisateur);
	        optUtilisateur = Optional.of(utilisateur);
	    } catch (EmptyResultDataAccessException exc) {
	    	System.err.println(exc.getMessage());
	    	exc.printStackTrace();
	        optUtilisateur = Optional.empty();
	    }
	    System.err.println(optUtilisateur);
	    return optUtilisateur;
	}

		

//		@Override
//		public Optional<Utilisateur> findUtilisateurByPseudo(String pseudo) {
//			
//			String sql = "select pseudo, mot_de_passe, administrateur from utilisateurs where pseudo=? ";
//					
//			Optional <Utilisateur> optUtilisateur = null;
//			
//			try {
//				Utilisateur utilisateur = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Utilisateur>(Utilisateur.class),
//						pseudo); 
//				optUtilisateur = Optional.of(utilisateur);
//				
//			} catch (EmptyResultDataAccessException exc) {
//				optUtilisateur = Optional.empty();
//			}
//			return optUtilisateur;
//		}
	
		
		
//		@Override
//		public Optional<Utilisateur> findUtilisateurByEmail(String email) {
//			
//			String sql = "select email, mot_de_passe, administrateur from utilisateurs where email=? ";
//			
//			Optional <Utilisateur> optUtilisateur = null;
	//	
//			try {
//				Utilisateur utilisateur = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Utilisateur>(Utilisateur.class),
//						email); 
//				optUtilisateur = Optional.of(utilisateur);
//				
//			} catch (EmptyResultDataAccessException exc) {
//				optUtilisateur = Optional.empty();
//			}
//			return optUtilisateur;
//		}
		
		
		
		
//	@Override	
//	public Optional<Utilisateur> findUtilisateurByNoUtilisateur(int noUtilisateur) {
//	    String sql = "SELECT no_utilisateur, pseudo, mot_de_passe, administrateur FROM utilisateurs WHERE no_utilisateur = ?";
//	    Optional<Utilisateur> optUtilisateur = null;
//	    try {
//	        Utilisateur utilisateur = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Utilisateur.class), noUtilisateur);
//	        optUtilisateur = Optional.of(utilisateur);
//	    } catch (EmptyResultDataAccessException exc) {
//	        optUtilisateur = Optional.empty();
//	    }
//	    return optUtilisateur;
//	}

	
		@Override
	    public Optional<Utilisateur> consulterCompteParId(int noUtilisateur)  {
	        String sql = "select no_utilisateur,pseudo, nom, prenom, email, telephone, rue, code_postal, ville,credit,mot_de_passe from utilisateurs where no_utilisateur = ? ";
	        Optional<Utilisateur> optUtilisateur = null;
	        try {
	            Utilisateur utilisateur = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Utilisateur>(Utilisateur.class), noUtilisateur);
	            optUtilisateur = Optional.of(utilisateur);
	        }catch(EmptyResultDataAccessException exc) {
	            optUtilisateur = Optional.empty();
	        }
	        return optUtilisateur;
	    }
		
		
	
	@Override
	public void supprimerCompte(int noUtilisateur) throws UtilisateurNotFoundRuntimeException  {
		String sql = "delete from utilisateurs where id=?";
		int nbLignes = jdbcTemplate.update(sql, noUtilisateur);
		if(nbLignes == 0) {
			throw new UtilisateurNotFoundRuntimeException();
		}
		
	}


	@Override
	public Utilisateur saveUtilisateur(Utilisateur utilisateur) throws UtilisateurNotFoundRuntimeException {
		System.out.println("UtilisateurRepositoryImpl.saveUtilisateur()");
		if (utilisateur.getNoUtilisateur() == null){
			//Ajout d'un nouvel utilisateur
			String sql = "insert into Utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)"
					 +" values (:pseudo, :nom, :prenom, :email, :telephone, :rue, :code_postal, :ville, :mot_de_passe, :credit, :administrateur)";


			MapSqlParameterSource parameterSource = new MapSqlParameterSource();
			
			parameterSource.addValue("pseudo", utilisateur.getPseudo());
			parameterSource.addValue("nom", utilisateur.getNom());
			parameterSource.addValue("prenom", utilisateur.getPrenom());
			parameterSource.addValue("email", utilisateur.getEmail());
			parameterSource.addValue("telephone", utilisateur.getTelephone());
			parameterSource.addValue("rue", utilisateur.getRue());
			parameterSource.addValue("code_postal", utilisateur.getCodePostal());
			parameterSource.addValue("ville", utilisateur.getVille());			
			parameterSource.addValue("mot_de_passe", utilisateur.getMotDePasse());
			parameterSource.addValue("credit", utilisateur.getCredit());
			parameterSource.addValue("administrateur", utilisateur.isAdministrateur());
			
			//Le conteneur qui va recevoir la clé primaire
			//générée par la base de données
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			namedParameterjdbcTemplate.update(sql, parameterSource, keyHolder, 
					new String[] {"no_utilisateur"});
			
			utilisateur.setNoUtilisateur(keyHolder.getKey().intValue());
		}else {
			int nbLignes =0;
			if(utilisateur.getConfirmMotDePasse().isBlank()) {				
				String sql = "update Utilisateurs set pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, credit = ?, administrateur = ? where no_utilisateur = ?";     
				nbLignes = jdbcTemplate.update(sql, utilisateur.getPseudo(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getTelephone(), utilisateur.getRue(), utilisateur.getCodePostal(), utilisateur.getVille(), utilisateur.getCredit(), utilisateur.isAdministrateur(), utilisateur.getNoUtilisateur());
			}else {
				String sql = "update Utilisateurs set pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ? ,credit = ?, administrateur = ? where no_utilisateur = ?";     
				nbLignes = jdbcTemplate.update(sql, utilisateur.getPseudo(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getTelephone(), utilisateur.getRue(), utilisateur.getCodePostal(), utilisateur.getVille(),utilisateur.getMotDePasse(), utilisateur.getCredit(), utilisateur.isAdministrateur(), utilisateur.getNoUtilisateur());				
			}
			if(nbLignes == 0) {
			    throw new UtilisateurNotFoundRuntimeException();
			}
			
		}
		return utilisateur;
	}


	class UserRowMapper  implements RowMapper<Utilisateur>{

		@Override
		public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
			Utilisateur user = new Utilisateur();
			user.setNoUtilisateur(rs.getInt("no_utilisateur"));
			user.setPseudo(rs.getString("pseudo"));
			user.setEmail(rs.getString("email"));
			user.setMotDePasse(rs.getString("mot_de_passe"));
			System.err.println("UtilisateurRepositoryImpl.UserRowMapper.mapRow()");
			System.out.println(user);
			return user;
		}
		
		
		
	}
	
	
}
