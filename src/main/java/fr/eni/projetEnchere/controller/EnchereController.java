package fr.eni.projetEnchere.controller;


import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

	/*
	 * private EnchereService enchereService; private CategorieRepository
	 * categorieRepository;
	 * 
	 * @Autowired public CategorieController(EnchereService enchereService) {
	 * this.categorieRepository = categorieRepository; }
	 */
	
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
        public String listeEncheres(Model model) {
			

        	List<Categorie> categories = this.enchereService.getAllCategories();
        	System.out.println(categories);
        	model.addAttribute("categories", categories);
        	return "encheres";

        }
		
		
		/*
		 * @GetMapping("/newVente") public String newVente() { return "newVente"; }
		 */
	  

		@GetMapping("/newVente")
      public String creationVente(Model model) {
      	List<Categorie> categories = this.enchereService.getAllCategories();
      	
      	model.addAttribute("categories", categories);
		model.addAttribute("articleVendu", new ArticleVendu());
		model.addAttribute("utilisateur", utilisateur);

      	return "newVente";

      }
		
		@PostMapping("/newVente")
		public String creerEnchere( @ModelAttribute("articleVendu") ArticleVendu articleVendu, @AuthenticationPrincipal Utilisateur connectedUser ) {
	        
			/*
			 * if (bindingResult.hasErrors()) { System.out.println("C");
			 * 
			 * return "newVente"; }
			 */
			
			articleVendu.setVendeur(connectedUser);
	        this.enchereService.ajouterArticleVendu(articleVendu);
	        System.out.println(articleVendu);

			return "redirect:/encheres";
		}

    
	/*
	 * 
	 * 
	 * //Get mapping pose problème
	 * 
	 * @GetMapping("/encheres") public String getAllCategories(Model model) {
	 * 
	 * List<Categorie> categories = this.enchereService.getAllCategories();
	 * System.out.println(categories); model.addAttribute("categories", categories);
	 * 
	 * return "encheres"; }
	 */
    
    
}
