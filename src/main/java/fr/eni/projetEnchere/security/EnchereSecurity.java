package fr.eni.projetEnchere.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class EnchereSecurity {
	 
			@Bean
			public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
				http
					.authorizeHttpRequests((requests) -> requests				
						//.requestMatchers("/encheres","/creerProfil","/css/*","/fragment/*").permitAll()
						//.requestMatchers("/newVente","/detailVente","/modifierProfil","/profilUser","/venteEffectuee","/venteGagne").hasRole("UTILISATEUR")
						//.requestMatchers("/modifierProfil").hasRole("ADMINISTRATEUR")
						.anyRequest().permitAll()
					)
					.formLogin((form) -> form
						.loginPage("/connexion")
						.permitAll()
						.defaultSuccessUrl("/encheres")
					)
					
					.logout((logout) -> logout.permitAll()
							.logoutSuccessUrl("/encheres"));
	
				return http.build();
			}
		
		@Bean
		public BCryptPasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
	}