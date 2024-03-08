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
import fr.eni.projetEnchere.bo.Enchere;
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

	@GetMapping("/connexion")
	public String afficherConnexion() {
		return "connexion";
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

	@GetMapping("/profilUser")
	public String profilUser(Model model, @AuthenticationPrincipal Utilisateur connectedUser) {
		Utilisateur u = this.enchereService.consulterCompteParId(connectedUser.getNoUtilisateur());
		System.out.println(u);
		model.addAttribute("utilisateur", u);
		return "profilUser";
	}
	
	@GetMapping("/consulterProfil")
	public String consulterProfil(Model model, @AuthenticationPrincipal Utilisateur connectedUser) {
		Utilisateur u = this.enchereService.consulterCompteParId(connectedUser.getNoUtilisateur());
		System.out.println(u);
		model.addAttribute("utilisateur", u);
		return "consulterProfil";
	}

	@GetMapping("/modifierProfil")
	public String modifierProfil(Model model, @AuthenticationPrincipal Utilisateur connectedUser) {
		Utilisateur u = this.enchereService.consulterCompteParId(connectedUser.getNoUtilisateur());
		System.err.println(u);
		model.addAttribute("utilisateur", u);
		return "modifierProfil";
	}

	@PostMapping("/modifierProfil")
	public String modifierProfil(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur,
			BindingResult bindingResult, @RequestParam("newPassword") String newPassword,
			@AuthenticationPrincipal Utilisateur connectedUser) {

		// Vérifier que le mot de passe actuel correspond au mot de passe enregistré en
		// base de données
		if (!passwordEncoder.matches(utilisateur.getMotDePasse(), connectedUser.getMotDePasse())) {
			bindingResult.rejectValue("motDePasse", "Mot de passe actuel incorrect.");
		}
		// si des erreurs sont détectées, on revient sur le formulaire
		if (bindingResult.hasErrors()) {
			System.err.println(bindingResult.getAllErrors());
			return "modifierProfil";
		}

		if (newPassword != null && newPassword.equals(utilisateur.getConfirmMotDePasse())) {
			utilisateur.setMotDePasse(newPassword);
		}

		utilisateur.setNoUtilisateur(connectedUser.getNoUtilisateur());
		enchereService.modifierCompte(utilisateur);
		return "redirect:/profilUser";
	}

	@PostMapping("/supprimerProfil")
	public String suppressionCompte(@RequestParam("id") int noUtilisateur) {
//		if() {
//			System.out.println("Vous ne pouvez pas supprimer le compte");
//		}else {
		enchereService.supprimerCompte(noUtilisateur);
		return "redirect:/encheres";
		}
//	}

	


//	@GetMapping("/newVente")
//	public String newVente() {
//		return "newVente";
//	}
//
//
//	@GetMapping("/venteEffectuee")
//	public String venteEffectuee() {
//		return "venteEffectuee";
//	}
//
//	@GetMapping("/venteGagnee")
////	public String venteGagnee() {
////		return "venteGagnee";
////	}

}
