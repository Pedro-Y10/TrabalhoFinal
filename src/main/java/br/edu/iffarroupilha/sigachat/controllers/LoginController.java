package br.edu.iffarroupilha.sigachat.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iffarroupilha.sigachat.modelos.Usuario;
import br.edu.iffarroupilha.sigachat.modelos.dto.TokenDTO;
import br.edu.iffarroupilha.sigachat.modelos.dto.UsuarioDTO;
import br.edu.iffarroupilha.sigachat.modelos.repositorios.UsuarioRepositorio;
import br.edu.iffarroupilha.sigachat.services.JWTService;

/**
 * <p>
 * Controller para autenticar usuario e criar novas contas
 * </p>
 * 
 * @author Professor
 * @since Jan 8, 2025 7:42:31 PM
 */

@Controller

@RequestMapping("/login")
public class LoginController {
	@Autowired
	private UsuarioRepositorio repositorio;
	@Autowired
	private AuthenticationManager sessionManagment;
	@Autowired
	private JWTService jwtService;

	@PostMapping("/entrar")
	public ResponseEntity entrar(@RequestBody UsuarioDTO dto) {

		return processLogin(dto.email(), dto.senha());
	}

	@PostMapping("/ws")
	public ResponseEntity entrarWebSocket(@RequestParam Map<String, String> dto) {

		return processLogin(dto.get("email"), dto.get("senha"));

	}

	private ResponseEntity processLogin(String email, String senha) {
		var username = new UsernamePasswordAuthenticationToken(email, senha);

		var auth = sessionManagment.authenticate(username);

		String token = jwtService.generateToken((Usuario) auth.getPrincipal());

		return ResponseEntity.ok().body(new TokenDTO(token));

	}

	@PostMapping("/cadastrar")
	public ResponseEntity cadastrar(@RequestBody UsuarioDTO dto) {

		Usuario usu = repositorio.findByEmail(dto.email());
		if (usu != null) {
			return ResponseEntity.badRequest().body("Usuario já existe");
		}

		// insere novo usuario
		usu = new Usuario(dto);
		// encripta a senha
		var encoder = new BCryptPasswordEncoder();

		usu.setSenha(encoder.encode(dto.senha()));

		repositorio.save(usu);

		return ResponseEntity.ok().body(usu);
	}
}
