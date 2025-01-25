package br.edu.iffarroupilha.sigachat.modelos.dto;

import java.util.Date;

import br.edu.iffarroupilha.sigachat.modelos.Grupo;
import br.edu.iffarroupilha.sigachat.modelos.Usuario;

/**
 * <p>
 * Objeto de transferencia para mensagem
 * </p>
 * 
 * @author Professor
 * @since Nov 1, 2024 7:50:47 PM
 */

public record MensagemDTO(Long idMensagem,String conteudo, Usuario remente, Usuario destinatario, Grupo grupoDestinatario, Date data) {

}
