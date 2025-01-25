package br.edu.iffarroupilha.sigachat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iffarroupilha.sigachat.modelos.Mensagem;
import br.edu.iffarroupilha.sigachat.modelos.dto.MensagemDTO;
import br.edu.iffarroupilha.sigachat.services.MensagemService;

/**
 * <p>
 * Controle para gerir as requisições HTTTP da API para a entiodade mensgem
 * </p>
 * 
 * @author Professor
 * @since Nov 1, 2024 8:09:43 PM
 */
@RestController
@RequestMapping("/mensagem")
public class MensagemControle {
	@Autowired
	private MensagemService service ;
	
	
	
	@PostMapping
	public void enviar( @RequestBody MensagemDTO dto) {
		
		Mensagem msg = new Mensagem(dto);
		this.service.enviarMensagem(msg);
	}
}
