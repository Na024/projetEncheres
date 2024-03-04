package fr.eni.projetEnchere.bll;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fr.eni.projetEnchere.bo.Utilisateur;
import fr.eni.projetEnchere.dal.UtilisateurRepository;
import fr.eni.projetEnchere.exceptions.UtilisateurNotFoundRuntimeException;
@Service
public class EnchereServiceImpl implements EnchereService {
	
private UtilisateurRepository utilisateurRepository;
private BCryptPasswordEncoder passwordEncoder;
	

	public EnchereServiceImpl(UtilisateurRepository utilisateurRepository, BCryptPasswordEncoder passwordEncoder) {

	this.utilisateurRepository = utilisateurRepository;
	this.passwordEncoder = passwordEncoder;
}

	@Override
	public Utilisateur creerCompte(Utilisateur utilisateur) {
	    try {
	    	// Hachez le mot de passe avec bcrypt avant de le stocker dans la base de données
	    	 String motDePasseEncode = passwordEncoder.encode(utilisateur.getMotDePasse());
	         utilisateur.setMotDePasse(motDePasseEncode);
	      // Enregistrez l'utilisateur dans la base de données
	         Utilisateur u = utilisateurRepository.saveUtilisateur(utilisateur);
	        return u;
	    } catch (UtilisateurNotFoundRuntimeException e) {
	        // Gérer l'exception ici, par exemple, en journalisant l'erreur ou en effectuant d'autres actions nécessaires.
	        e.printStackTrace(); // Imprime la trace de la pile de l'exception
	        return null; // Ou lancez une nouvelle exception, ou retournez une valeur par défaut, selon votre logique de gestion d'erreur.
	    }
	}

//	@Override
//	public Utilisateur consulterCompteParId(int noUtilisateur)  {
//		try {
//	    Optional<Utilisateur> optUtilisateur = utilisateurRepository.findUtilisateurByNoUtilisateur(noUtilisateur);
//	    return optUtilisateur.orElseThrow(NoSuchElementException::new);
//		}catch (NoSuchElementException e) {
//			 // Gérer l'exception ici, par exemple, en journalisant l'erreur ou en effectuant d'autres actions nécessaires.
//	        e.printStackTrace(); // Imprime la trace de la pile de l'exception
//	        throw new UtilisateurNotFoundRuntimeException(); // Ou lancez une nouvelle exception, selon votre logique de gestion d'erreur.
//	    }
//	}
	
	public Utilisateur findUtilisateurByPseudoOuEmail(String identifiant)  {
		try {
	    Optional<Utilisateur> optUtilisateur = utilisateurRepository.findUtilisateurByPseudoOuEmail(identifiant);
	    return optUtilisateur.orElseThrow(NoSuchElementException::new);
		}catch (NoSuchElementException e) {
			 // Gérer l'exception ici, par exemple, en journalisant l'erreur ou en effectuant d'autres actions nécessaires.
	        e.printStackTrace(); // Imprime la trace de la pile de l'exception
	        throw new UtilisateurNotFoundRuntimeException(); // Ou lancez une nouvelle exception, selon votre logique de gestion d'erreur.
	    }
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
