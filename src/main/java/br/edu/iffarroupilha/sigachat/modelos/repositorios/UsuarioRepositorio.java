package br.edu.iffarroupilha.sigachat.modelos.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.edu.iffarroupilha.sigachat.modelos.Usuario;

/**
 * <p>
 * Interface que representa/especifica todas as operações no banco de dados
 * envolvendo a entidade usuario
 * </p>
 * 
 * @author Professor
 * @since Nov 1, 2024 7:36:36 PM
 */
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
	/**
	 * Busca todos os usuario por um nome especifico
	 * @param nome
	 * @return lista de usuarios
	 */
	public List<Usuario> findByNome(String nome);
	
	public <T extends UserDetails> T findByEmail(String email);

}
