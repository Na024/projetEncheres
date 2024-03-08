package fr.eni.projetEnchere.converter;




import org.springframework.core.convert.converter.Converter;

import fr.eni.projetEnchere.bll.EnchereService;
import fr.eni.projetEnchere.bo.Categorie;

public class StringToCategorie implements Converter<String, Categorie>{
	

	private EnchereService enchereService;
	
	public StringToCategorie(EnchereService enchereService) {
		this.enchereService = enchereService;
	}
	
	@Override
	public Categorie convert(String source) {
		System.out.println("convert fonctionne");
		int noCategorie = Integer.parseInt(source);
		Categorie categorie = enchereService.consulterCategorieParId(noCategorie);
		System.err.println(categorie);
		return categorie;
	}
	
	
	
}
