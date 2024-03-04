package fr.eni.projetEnchere.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eni.projetEnchere.bll.EnchereService;
import fr.eni.projetEnchere.bo.Utilisateur;
import fr.eni.projetEnchere.exceptions.UtilisateurNotFoundRuntimeException;
import jakarta.validation.Valid;
@Controller
public class UtilisateurController {
	
private EnchereService enchereService;
private BCryptPasswordEncoder passwordEncoder;
	
	
	public UtilisateurController(EnchereService enchereService, BCryptPasswordEncoder passwordEncoder) {
	super();
	this.enchereService = enchereService;
	this.passwordEncoder = passwordEncoder;
}



		
	  
	  @GetMapping("/creerProfil") 
	  public String creerProfil(Model model) {
		  model.addAttribute("utilisateur", new Utilisateur());
	  return "creerProfil"; 
	  }
	  
	  
//	  @PostMapping ("/creerProfil")
//	  public String creerUnProfil (@ModelAttribute("utilisateur")Utilisateur utilisateur){
//		  	String motDePasseEncode = passwordEncoder.encode(utilisateur.getMotDePasse());
//		  	utilisateur.setMotDePasse(motDePasseEncode);
//			System.out.println(utilisateur);
//			this.enchereService.creerCompte(utilisateur);
//	
//		  return "redirect:/encheres";
//	  }
	  
	  @PostMapping("/creerProfil")
	    public String creerUnProfil(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur,
	            BindingResult bindingResult) {
	        
	        // si des erreurs sont détectées, on revient sur le formulaire
	        if (bindingResult.hasErrors()) {
	            return "creerProfil";
	        } 
	        String motDePasseEncode = passwordEncoder.encode(utilisateur.getMotDePasse());
	        utilisateur.setMotDePasse(motDePasseEncode);
	        System.out.println(utilisateur);
	        this.enchereService.creerCompte(utilisateur);

	        return "redirect:/encheres";

	    }

	  @GetMapping("/connexion") 
	  public String afficherConnexion() {
	  return "connexion"; 
	  }
	   
	  @GetMapping("/detailVente") 
	  public String detailVente() {
	  return "detailVente"; 
	  }
	  
	  @GetMapping("/modifierProfil") 
	  public String modifierProfil() {
	  return "modifierProfil"; 
	  }
	  @GetMapping("/newVente") 
	  public String newVente() {
	  return "newVente"; 
	  }
	  
	  @GetMapping("/profilUser") 
	  public String profilUser() {
	  return "profilUser"; 
	  }
	  @GetMapping("/venteEffectuee") 
	  public String venteEffectuee() {
	  return "venteEffectuee"; 
	  }
	  @GetMapping("/venteGagnee") 
	  public String venteGagnee() {
	  return "venteGagnee"; 
	  }
	 
	  
	  
	 
}
