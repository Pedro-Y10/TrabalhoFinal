package br.edu.iffarroupilha.sigachat.modelos.dto;

/**
 * <p>
 * Objeto de transporte para o usuário
 * </p>
 * 
 * @author Professor
 * @since Nov 1, 2024 7:45:41 PM
 */

public record UsuarioDTO(String email, String nome, String senha, String status, String foto) {

}
