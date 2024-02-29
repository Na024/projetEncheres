package fr.eni.projetEnchere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import fr.eni.projetEnchere.bll.EnchereService;
@Controller
public class UtilisateurController {
private EnchereService enchereService;
	
	public UtilisateurController (EnchereService enchereService) {
		this.enchereService = enchereService;
	}

	
	@GetMapping("/encheres")
	public String afficherEncheres () {
			return "encheres";
	}
	
	  @GetMapping("/connexion") 
	  public String afficherConnexion() {
	  return "connexion"; 
	  }
	  
	  @GetMapping("/creerProfil") 
	  public String creerProfil() {
	  return "creerProfil"; 
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
