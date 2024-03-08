package fr.eni.projetEnchere.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import fr.eni.projetEnchere.bll.EnchereService;
import fr.eni.projetEnchere.converter.StringToCategorie;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private EnchereService enchereService;
	
	public WebConfig(EnchereService enchereService) {
		this.enchereService = enchereService;
	}
	
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToCategorie(enchereService));
    }
}