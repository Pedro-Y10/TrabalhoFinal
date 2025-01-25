package br.edu.iffarroupilha.sigachat.configuration;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


/**
 * <p>
 * Classe responsavel por configurar aspectos de segurança associados a API
 * </p>
 * 
 * @author Professor
 * @since Jan 8, 2025 7:27:32 PM
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {

	@Autowired
	private SpringUserFilter filtroUsuario;

	@Bean
	public SecurityFilterChain getFilter(HttpSecurity sec) throws Exception {
		return sec.csrf(csrf -> csrf.disable())
	            .cors(cors -> cors.configurationSource(corsConfigurationSource())

				)
				.authorizeHttpRequests(http -> http.requestMatchers(HttpMethod.POST, "/login/entrar").permitAll()
						.requestMatchers(HttpMethod.POST, "/login/cadastrar").permitAll()
						.requestMatchers(HttpMethod.POST, "/login/ws").permitAll()
						.requestMatchers("/ws/**").permitAll()
						.anyRequest().authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(filtroUsuario, UsernamePasswordAuthenticationFilter.class).build();

	}

	@Bean
	public AuthenticationManager getManager(AuthenticationConfiguration cfg) throws Exception {
		return cfg.getAuthenticationManager();

	}

	@Bean
	public PasswordEncoder getEncouderPadrao() {
		return new BCryptPasswordEncoder();
	}


	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Collections.singletonList("*")); // Permitir qualquer origem
	    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));
	    configuration.setAllowCredentials(false); // Ajuste para false se não precisar de credenciais

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}
}
