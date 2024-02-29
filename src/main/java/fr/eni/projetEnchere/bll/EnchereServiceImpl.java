package fr.eni.projetEnchere.bll;

import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.eni.projetEnchere.bo.Utilisateur;
import fr.eni.projetEnchere.dal.UtilisateurRepository;
import fr.eni.projetEnchere.exceptions.UtilisateurNotFoundRuntimeException;
@Service
public class EnchereServiceImpl implements EnchereService {
private UtilisateurRepository utilisateurRepository;
	
	public EnchereServiceImpl (UtilisateurRepository utilisateurRepository) {
		this.utilisateurRepository = utilisateurRepository;
	}
	
	
	
	

	@Override
	public Utilisateur creerCompte(Utilisateur utilisateur) throws UtilisateurNotFoundRuntimeException {
		Utilisateur u = utilisateurRepository.saveUtilisateur (utilisateur);
		return u;
		
	}

	@Override
	public Utilisateur consulterCompteParId(int noUtilisateur) throws UtilisateurNotFoundRuntimeException {
	    Optional<Utilisateur> optUtilisateur = utilisateurRepository.findUtilisateurByNoUtilisateur(noUtilisateur);
	    if (optUtilisateur.isPresent()) {
	    	return optUtilisateur.get();
	    }
	    throw new UtilisateurNotFoundRuntimeException();
	}
	
	
	
	
	@Override
	public void modifierCompte(Utilisateur utilisateur) throws UtilisateurNotFoundRuntimeException {
		utilisateurRepository.saveUtilisateur(utilisateur);
		
	}



	@Override
	public void supprimerCompte(int noUtilisateur) throws UtilisateurNotFoundRuntimeException {
		utilisateurRepository.supprimerCompte(noUtilisateur);
		
	}






}
