package br.edu.iffarroupilha.sigachat.modelos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.iffarroupilha.sigachat.modelos.Preferencias;

/**
 * <p>
 * Interface com os padroes de persistencias
 * </p>
 * 
 * @author Professor
 * @since Nov 6, 2024 8:22:29 PM
 */

public interface PreferenciasRepositorio extends JpaRepository<Preferencias, Long> {

}
