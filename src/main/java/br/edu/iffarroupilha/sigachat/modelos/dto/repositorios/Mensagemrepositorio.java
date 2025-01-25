package br.edu.iffarroupilha.sigachat.modelos.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.iffarroupilha.sigachat.modelos.Mensagem;
import br.edu.iffarroupilha.sigachat.modelos.Usuario;

/**
 * <p>
 * Interface para definir os metodos de consulta da entidade mensagem
 * </p>
 * 
 * @author Professor
 * @since Nov 1, 2024 7:40:01 PM
 */

public interface Mensagemrepositorio extends JpaRepository<Mensagem, Long> {

	/**
	 * <p>
	 * Busca todas as mensagens emviadas por um usuario erspecifico
	 * </p>
	 * 
	 * @param usu que enviou a mensagem
	 * @return lista com as mensagens
	 */
	public List<Mensagem> findByRemetente(Usuario usu);
}
