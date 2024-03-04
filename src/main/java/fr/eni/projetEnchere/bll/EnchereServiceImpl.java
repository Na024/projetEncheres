package fr.eni.projetEnchere.bll;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.projetEnchere.bo.Categorie;
import fr.eni.projetEnchere.bo.Utilisateur;
import fr.eni.projetEnchere.dal.CategorieRepository;
import fr.eni.projetEnchere.dal.UtilisateurRepository;
import fr.eni.projetEnchere.exceptions.UtilisateurNotFoundRuntimeException;
@Service
public class EnchereServiceImpl implements EnchereService {
	private UtilisateurRepository utilisateurRepository;
	private CategorieRepository categorieRepository;
	
	public EnchereServiceImpl (UtilisateurRepository utilisateurRepository , CategorieRepository categorieRepository) {
		this.utilisateurRepository = utilisateurRepository;
		this.categorieRepository = categorieRepository;
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

	@Override
	public List<Categorie> getAllCategories() {
		
		return categorieRepository.a();
	}

	


	
	
	// CATEGORIE
	
	/*
	 * @Override public Categorie consulterCategorie(int noCategorie) {
	 * 
	 * return null; }
	 */
	/*
	 * @Autowired public EnchereServiceImpl(CategorieRepository categorieRepository)
	 * { this.categorieRepository = categorieRepository; }
	 * 
	 * 
	 * @Override public List<Categorie> getAllCategories() { return
	 * categorieRepository.getAllCategories(); }
	 */

}
