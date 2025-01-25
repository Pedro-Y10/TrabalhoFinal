package br.edu.iffarroupilha.sigachat.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iffarroupilha.sigachat.modelos.Usuario;
import br.edu.iffarroupilha.sigachat.modelos.dto.UsuarioDTO;
import br.edu.iffarroupilha.sigachat.services.UsuarioService;

/**
 * <p>
 * Controller para gerir as requisições http da API REST
 * </p>
 * 
 * @author Professor
 * @since Nov 1, 2024 8:08:35 PM
 */

@RestController
@RequestMapping("/usuario")
public class UsuarioControle {
	@Autowired
	private UsuarioService servico;

//	@PostMapping
//	@ResponseStatus(code = HttpStatus.CREATED)
//	public Usuario salvar(@RequestBody UsuarioDTO dto){
//		Usuario u = new Usuario();
//		u.setEmail(dto.email());
//		u.setNome(dto.nome());
//		return servico.salvar(u);  
//	}
	@PostMapping
	public ResponseEntity<Usuario> salvar(@RequestBody UsuarioDTO dto) {
		Usuario u = new Usuario();
		u.setEmail(dto.email());
		u.setNome(dto.nome());
		return ResponseEntity.status(HttpStatus.CREATED).body(servico.gravar(u));
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(servico.buscarTodos());
	}
}
