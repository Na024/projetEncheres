package fr.eni.projetEnchere.bll;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fr.eni.projetEnchere.bo.ArticleVendu;
import fr.eni.projetEnchere.bo.Categorie;
import fr.eni.projetEnchere.bo.Utilisateur;
import fr.eni.projetEnchere.dal.CategorieRepository;
import fr.eni.projetEnchere.dal.EnchereRepository;
import fr.eni.projetEnchere.dal.UtilisateurRepository;
import fr.eni.projetEnchere.dal.impl.EnchereRepositoryImpl;
import fr.eni.projetEnchere.exceptions.ArticleNotFoundRuntimeException;
import fr.eni.projetEnchere.exceptions.UtilisateurNotFoundRuntimeException;
@Service
public class EnchereServiceImpl implements EnchereService {

	private UtilisateurRepository utilisateurRepository;
	private EnchereRepository enchereRepository;
	private CategorieRepository categorieRepository;
	private BCryptPasswordEncoder passwordEncoder;
	
	

	public EnchereServiceImpl(UtilisateurRepository utilisateurRepository, EnchereRepository enchereRepository,
			CategorieRepository categorieRepository, BCryptPasswordEncoder passwordEncoder) {
		super();
		this.utilisateurRepository = utilisateurRepository;
		this.enchereRepository = enchereRepository;
		this.categorieRepository = categorieRepository;
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

	@Override
	public Utilisateur consulterCompteParId(int noUtilisateur)  {
		Optional<Utilisateur> optUtilisateur = utilisateurRepository.consulterCompteParId(noUtilisateur);
		if (optUtilisateur.isPresent()) {
			return optUtilisateur.get();
		}
		throw new UtilisateurNotFoundRuntimeException();
	}
	
	@Override
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

	@Override
	public List<Categorie> getAllCategories() {
		
		return categorieRepository.a();
	}


	@Override
	public ArticleVendu ajouterArticleVendu(ArticleVendu articleVendu) {
		ArticleVendu article = enchereRepository.saveArticleVendu(articleVendu);
		

		return article;
		
		

		
	}

	@Override
	public Categorie consulterCategorieParId(int noCategorie) {
		Optional<Categorie> optCategorie = categorieRepository.consulterCategorieParId(noCategorie);
		
		/*
		 * if (optCategorie.isPresent()) { return optCategorie.get(); } throw new
		 * CategorieNotFoundRuntimeException();
		 */
		
		return optCategorie.orElseThrow(NoSuchElementException::new);
		
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
