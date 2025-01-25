package br.edu.iffarroupilha.sigachat.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iffarroupilha.sigachat.modelos.Usuario;
import br.edu.iffarroupilha.sigachat.modelos.repositorios.UsuarioRepositorio;

/**
 * <p>
 * Classe de lógica de negócio do usuário
 * </p>
 * 
 * @author Professor
 * @since Nov 1, 2024 7:54:13 PM
 */
@Service
public class UsuarioService extends AService {
	
	private UsuarioRepositorio repositorio;

	public UsuarioService(UsuarioRepositorio repositorio) {
		super(repositorio);
		this.repositorio = repositorio;
	}
	

	/**
	 * <p>
	 * Busca todos os usuario com um nome especifico, trazendo apenas aqueles cujo
	 * nome sejam iguais ao termo digitado
	 * </p>
	 * 
	 * @param nome
	 * @return
	 */
	public List<Usuario> buscaPorNome(String nome) {
		return this.repositorio.findByNome(nome);
	}

	/**
	 * <p>
	 * Busca todos os usuário cadastrados, ordenados pelo nome
	 * </p>
	 * 
	 * @return lista de usuario
	 */
	public List<Usuario> buscarTodos() {
		return this.repositorio.findAll();
	}
}
