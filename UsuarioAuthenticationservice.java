package br.edu.iffarroupilha.sigachat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.edu.iffarroupilha.sigachat.modelos.repositorios.UsuarioRepositorio;
/**
 * <p>
 * Carrega um usuario a partir do seu login, no caso do sigaChat é campo email
* </p>
* @author Professor
* @since Jan 10, 2025 7:17:54 PM
*/
@Service
public class UsuarioAuthenticationservice implements UserDetailsService {

	@Autowired
	private UsuarioRepositorio repositorio;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// busca no  banco o usuario pelo email
		return repositorio.findByEmail(email);
	}

}
