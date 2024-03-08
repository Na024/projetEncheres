package fr.eni.projetEnchere.controller;

import java.security.Principal;
import java.util.ArrayList;



import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import fr.eni.projetEnchere.bll.EnchereService;
import fr.eni.projetEnchere.bo.ArticleVendu;
import fr.eni.projetEnchere.bo.Categorie;
import fr.eni.projetEnchere.bo.Utilisateur;
import fr.eni.projetEnchere.dal.CategorieRepository;
import fr.eni.projetEnchere.dal.EnchereRepository;

@Controller
public class EnchereController {

	

	private EnchereService enchereService;
	private CategorieRepository categorieRepository;
	private EnchereRepository enchereRepository; 
	private Utilisateur utilisateur;




		public EnchereController(EnchereService enchereService, CategorieRepository categorieRepository,
			EnchereRepository enchereRepository, Utilisateur utilisateur) {

		super();
		this.enchereService = enchereService;
		this.categorieRepository = categorieRepository;
		this.enchereRepository = enchereRepository;
		this.utilisateur = utilisateur;

	}


		@GetMapping("/encheres")
        public String listeEncheres(@RequestParam(name= "motCle",required = false) String motCle,@RequestParam(name= "noCategorie",required = false)  Integer noCategorie, Model model) {
			 List<ArticleVendu> articleVendus = new ArrayList<>();
			if (motCle != null && noCategorie != null) {
				
			  articleVendus = enchereService.findArticleByMotCleAndCategorie(motCle, noCategorie);
			  model.addAttribute("articleVendus", articleVendus);
		}
			
        	model.addAttribute("motCle", motCle);
        	model.addAttribute("noCategorie", noCategorie);
        	
			List<Categorie> categories = this.enchereService.getAllCategories();
			model.addAttribute("categories", categories);
			

        	return "encheres";

        }
		


		@GetMapping("/detailVente")
		public String afficherDetailVente (){
			return "detailVente";
			
		}
		
		
	  

		@GetMapping("/newVente")
      public String creationVente(Model model) {
      	List<Categorie> categories = this.enchereService.getAllCategories();
      	
      	model.addAttribute("categories", categories);
		model.addAttribute("articleVendu", new ArticleVendu());
		model.addAttribute("utilisateur", utilisateur);
		
		
	    return "newVente";
      }
		
		@PostMapping("/newVente")
		public String creerEnchere( @ModelAttribute("articleVendu") ArticleVendu articleVendu,
				
				@AuthenticationPrincipal Utilisateur connectedUser ) {
	        
			
			articleVendu.setVendeur(connectedUser);
	        this.enchereService.ajouterArticleVendu(articleVendu);
	        System.out.println(articleVendu);

			return "redirect:/encheres";
		}

    
    
}

