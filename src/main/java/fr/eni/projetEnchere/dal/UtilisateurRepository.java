package fr.eni.projetEnchere.dal;

import java.util.Optional;

import fr.eni.projetEnchere.bo.Utilisateur;
import fr.eni.projetEnchere.exceptions.UtilisateurNotFoundRuntimeException;

public interface UtilisateurRepository {

	
	Optional<Utilisateur> consulterCompteParId(int noUtilisateur);
	
	void supprimerCompte(int noUtilisateur) throws UtilisateurNotFoundRuntimeException;

	Utilisateur saveUtilisateur(Utilisateur utilisateur) throws UtilisateurNotFoundRuntimeException;

	Optional<Utilisateur> findUtilisateurByPseudoOuEmail(String identifiant);
	
	
	
	
}
