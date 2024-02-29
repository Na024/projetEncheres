package fr.eni.projetEnchere.dal;

import java.util.Optional;

import fr.eni.projetEnchere.bo.Utilisateur;
import fr.eni.projetEnchere.exceptions.UtilisateurNotFoundRuntimeException;

public interface UtilisateurRepository {

Optional<Utilisateur> findUtilisateurByPseudo (String pseudo); 
	
	Optional<Utilisateur> findUtilisateurByEmail (String email);
	
	Optional<Utilisateur> findUtilisateurByNoUtilisateur (int	noUtilisateur);
	
	void supprimerCompte(int noUtilisateur) throws UtilisateurNotFoundRuntimeException;

	Utilisateur saveUtilisateur(Utilisateur utilisateur) throws UtilisateurNotFoundRuntimeException;
	
	
	
	
}
