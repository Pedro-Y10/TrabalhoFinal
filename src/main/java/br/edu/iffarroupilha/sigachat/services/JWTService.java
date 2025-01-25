package br.edu.iffarroupilha.sigachat.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.edu.iffarroupilha.sigachat.modelos.Usuario;

/**
 * <p>
 * Serviço para gerar e autenticar web tokens
 * </p>
 * 
 * @author Professor
 * @since Jan 8, 2025 8:17:42 PM
 */
@Service
public class JWTService {
	@Value("${sigachat.general.password}")
	private String senhaAplicacao;

	public String generateToken(Usuario usuario) {
		Algorithm algoritmo = Algorithm.HMAC256(senhaAplicacao);

		return JWT.create().withIssuer("SIGA-CHAT").withSubject(usuario.getEmail()).withExpiresAt(getExpirationDate())
				.sign(algoritmo);

	}

	private Date getExpirationDate() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.HOUR, 5);
		return c.getTime();
	}

	/**
	 * A partir de um token, faz a decriptaçao e retorna o email do usuario
	 * associado ao token
	 * 
	 * @param token
	 * @return
	 */
	public String decriptaToken(String token) {
		Algorithm algoritmo = Algorithm.HMAC256(senhaAplicacao);

		return JWT.require(algoritmo)
				.withIssuer("SIGA-CHAT")
				.build()
				.verify(token)
				.getSubject();

	}
}
