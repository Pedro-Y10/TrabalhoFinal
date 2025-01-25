package br.edu.iffarroupilha.sigachat.modelos.dto;
/**
 * <p>
 * Objeto de transferencia de dados para entidade preferencias
* </p>
* @author Professor
* @since Nov 6, 2024 8:20:41 PM
*/

public record PreferenciasDTO (long idPreferencias, 
		String tipoPreferencia, String valor) {
}
