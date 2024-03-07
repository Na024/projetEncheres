package fr.eni.projetEnchere.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.projetEnchere.bll.EnchereService;
import fr.eni.projetEnchere.bo.Utilisateur;
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

		// Valider la confirmation de mot de passe
		if (!utilisateur.getMotDePasse().equals(utilisateur.getConfirmMotDePasse())) {
			bindingResult.rejectValue("confirmMotDePasse", "Confirmation incorrecte.");
		}

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

	

	@GetMapping("/modifierProfil")
	public String modifierProfil() {
		return "modifierProfil";
	}
	
	
	@GetMapping("/newVente")
	public String newVente() {
		return "newVente";
	}

	@GetMapping("/profilUser")
	public String profilUser( Model model ,@AuthenticationPrincipal Utilisateur connectedUser ) {
		Utilisateur u = this.enchereService.consulterCompteParId(connectedUser.getNoUtilisateur());
		System.out.println(u);
		model.addAttribute("utilisateur", u);
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
