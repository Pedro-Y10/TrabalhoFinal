package br.edu.iffarroupilha.sigachat.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.edu.iffarroupilha.sigachat.modelos.Usuario;
import br.edu.iffarroupilha.sigachat.modelos.repositorios.UsuarioRepositorio;
import br.edu.iffarroupilha.sigachat.services.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * <p>
 * Carregar um usuario a partir do seu login ( email)
* </p>
* @author Professor
* @since Jan 10, 2025 7:15:01 PM
*/
/**
 * <p>
 * Componente executado a cada requisição para extrair o token de autenticação e
 * carregar o usuarioe suas permissões
 * </p>
 * 
 * @author Professor
 * @since Jan 10, 2025 7:43:16 PM
 */
@Component
public class SpringUserFilter extends OncePerRequestFilter {

	@Autowired
	private JWTService jwtService ;
	@Autowired
	private UsuarioRepositorio repositorio;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = extraiTokenFromRequest(request);
		if(token != null) {
			String email = jwtService.decriptaToken(token);
			Usuario usu = repositorio.findByEmail(email);
			
			var auth = new  UsernamePasswordAuthenticationToken(usu, null, usu.getAuthorities());
			
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		filterChain.doFilter(request, response);
	}

	private String extraiTokenFromRequest(HttpServletRequest request) {
		var header = request.getHeader("Authorization");
		if(header != null) {
			return header.replace("Bearer ", "");
		}
		return null;
	}

}
