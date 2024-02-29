package fr.eni.projetEnchere.dal;

import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.projetEnchere.bo.Utilisateur;
import fr.eni.projetEnchere.exceptions.UtilisateurNotFoundRuntimeException;
@Repository
public class UtilisateurRepositoryImpl implements UtilisateurRepository {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterjdbcTemplate;
	
	public UtilisateurRepositoryImpl(NamedParameterJdbcTemplate namedParameterjdbcTemplate) {
		super();
		this.namedParameterjdbcTemplate = namedParameterjdbcTemplate;
		this.jdbcTemplate = namedParameterjdbcTemplate.getJdbcTemplate();
	}

	
	@Override
	public Optional<Utilisateur> findUtilisateurByPseudo(String pseudo) {
		
		String sql = "select pseudo, motDePasse, administrateur from utilisateurs where pseudo=? ";
				
		Optional <Utilisateur> optUtilisateur = null;
		
		try {
			Utilisateur utilisateur = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Utilisateur>(Utilisateur.class),
					pseudo); 
			optUtilisateur = Optional.of(utilisateur);
			
		} catch (EmptyResultDataAccessException exc) {
			optUtilisateur = Optional.empty();
		}
		return optUtilisateur;
	}

	
	
	@Override
	public Optional<Utilisateur> findUtilisateurByEmail(String email) {
		
		String sql = "select email, motDePasse, administrateur from utilisateurs where email=? ";
		
		Optional <Utilisateur> optUtilisateur = null;
	
		try {
			Utilisateur utilisateur = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Utilisateur>(Utilisateur.class),
					email); 
			optUtilisateur = Optional.of(utilisateur);
			
		} catch (EmptyResultDataAccessException exc) {
			optUtilisateur = Optional.empty();
		}
		return optUtilisateur;
	}


	@Override	
	public Optional<Utilisateur> findUtilisateurByNoUtilisateur(int noUtilisateur) {
	    String sql = "SELECT no_utilisateur, pseudo, motDePasse, administrateur FROM utilisateurs WHERE no_utilisateur = ?";
	    Optional<Utilisateur> optUtilisateur = null;
	    try {
	        Utilisateur utilisateur = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Utilisateur.class), noUtilisateur);
	        optUtilisateur = Optional.of(utilisateur);
	    } catch (EmptyResultDataAccessException exc) {
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
		if (utilisateur.getNoUtilisateur() == null){
			//Ajout d'un nouvel utilisateur
			String sql = "insert into Utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)"
					 +" value (:pseudo, :nom, :prenom, :email, :telephone, :rue, :code_postal, :ville, :mot_de_passe, :credit, :administrateur)";
		
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
			String sql = "update Utilisateurs set pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, credit = ?, administrateur = ? where no_utilisateur = ?";
			int nbLignes = jdbcTemplate.update(sql, utilisateur.getPseudo(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getTelephone(), utilisateur.getRue(), utilisateur.getCodePostal(), utilisateur.getVille(), utilisateur.getMotDePasse(), utilisateur.getCredit(), utilisateur.isAdministrateur(), utilisateur.getNoUtilisateur());
			if(nbLignes == 0) {
			    throw new UtilisateurNotFoundRuntimeException();
			}
			
		}
		return utilisateur;
	}


	
	
	
}
