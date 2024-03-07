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
	public String modifierProfil(Model model,@AuthenticationPrincipal Utilisateur connectedUser) {
		Utilisateur u = this.enchereService.consulterCompteParId(connectedUser.getNoUtilisateur());
		System.err.println(u);
		model.addAttribute("utilisateur", u);
		return "modifierProfil";
	}
	
	@PostMapping("/modifierProfil")
	public String modifierProfil(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur,
								BindingResult bindingResult,		
								@RequestParam("newPassword") String newPassword,
			 					@AuthenticationPrincipal Utilisateur connectedUser) {

		//Utilisateur currentUser = this.enchereService.consulterCompteParId(connectedUser.getNoUtilisateur());
		//System.out.println(currentUser);
		System.err.println(connectedUser);
		System.out.println(utilisateur.getMotDePasse());
		System.err.println(passwordEncoder.matches(utilisateur.getMotDePasse(), connectedUser.getMotDePasse()));
		
		 // Vérifier que le mot de passe actuel correspond au mot de passe enregistré en base de données
	    if (!passwordEncoder.matches(utilisateur.getMotDePasse(), connectedUser.getMotDePasse())) {
	        bindingResult.rejectValue("motDePasse", "Mot de passe actuel incorrect.");
	    }
		// si des erreurs sont détectées, on revient sur le formulaire
		if (bindingResult.hasErrors()) {
			System.err.println(bindingResult.getAllErrors());
			return "modifierProfil";
		}
		
		if(newPassword != null && newPassword.equals(utilisateur.getConfirmMotDePasse())) {
			utilisateur.setMotDePasse(newPassword);
		}


	    utilisateur.setNoUtilisateur(connectedUser.getNoUtilisateur());
	    enchereService.modifierCompte(utilisateur);
		return "redirect:/profilUser";
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
