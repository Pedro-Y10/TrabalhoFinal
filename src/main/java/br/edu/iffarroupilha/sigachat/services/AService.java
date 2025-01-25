package br.edu.iffarroupilha.sigachat.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.iffarroupilha.sigachat.modelos.IEntidade;

/**
 * <p>
 * Modelo abstrato que generaliza as ações comums entre
 * as entidade
* </p>
* @author Professor
* @since Nov 6, 2024 7:41:50 PM
*/
public abstract class AService {
	// referencia ao repositorio da entidade
	private JpaRepository repositorio;
	/**
	 * Contrutor que recebe o repositorio especifico da 
	 * entidade
	 * @param repositorio
	 */
	public AService(JpaRepository repositorio ) {
		this.repositorio = repositorio;
	}
	
	/**
	 * <p>
	 * Gravar ou atulizar uma informação a nível de banco
	 * de dados
	 * @param entidade a ser salva
	 */
	public <T extends IEntidade> T gravar( IEntidade entidade) {
		return (T) repositorio.save( entidade );
	}
	
	/**
	 * <p>
	 * Apaga um registro do banco
	 * </p>
	 * @param entidade a ser apagada
	 */
	public void apagar( IEntidade entidade) {
		repositorio.delete( entidade );
	}
	/**
	 * <p>
	 * Lista todos os registros de uma entidade
	 * </p>
	 * @param entidade a ser apagada
	 */
	public List listar( ) {
		return this.repositorio.findAll();
	}
}
