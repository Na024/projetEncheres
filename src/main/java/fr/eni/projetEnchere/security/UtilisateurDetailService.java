package fr.eni.projetEnchere.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import fr.eni.projetEnchere.bo.Utilisateur;
import fr.eni.projetEnchere.dal.UtilisateurRepository;

@Component
public class UtilisateurDetailService implements UserDetailsService{
	private UtilisateurRepository utilisateurRepository;
	
	public UtilisateurDetailService(UtilisateurRepository utilisateurRepository) {
		this.utilisateurRepository = utilisateurRepository;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Vérifie d'abord si l'utilisateur existe en tant que pseudo
		Optional<Utilisateur> optUtilisateur = utilisateurRepository.findUtilisateurByPseudoOuEmail(username);
		
		if (optUtilisateur.isEmpty()) {
			
				throw new UsernameNotFoundException("Utilisateur non trouvé :" +username);
			}
			
		// Utilisateur trouvé
		Utilisateur utilisateur = optUtilisateur.get();
		
		// Création de l'utilisateur Spring Security
		UserBuilder userBuilder = User.withUsername(utilisateur.getPseudo())
				.password(utilisateur.getMotDePasse())
				.roles("UTILISATEUR");
		
	    // Ajout du rôle d'administrateur si l'utilisateur est administrateur
		if (utilisateur.isAdministrateur()) {
			userBuilder.roles("UTILISATEUR","ADMINISTRATEUR");
		}
		UserDetails user = userBuilder.build();
		
		return user;
	}

	
}