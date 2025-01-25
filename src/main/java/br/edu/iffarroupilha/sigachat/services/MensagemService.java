package br.edu.iffarroupilha.sigachat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iffarroupilha.sigachat.modelos.Mensagem;
import br.edu.iffarroupilha.sigachat.modelos.repositorios.Mensagemrepositorio;

/**
 * <p>
 * Classe de lógica de negócio da entidade Mensagem
* </p>
* @author Professor
* @since Nov 1, 2024 8:02:00 PM
*/
@Service
public class MensagemService extends AService{
	
	private Mensagemrepositorio repositorio;
	
	public MensagemService(Mensagemrepositorio repositorio) {
		super(repositorio);
		this.repositorio = repositorio;
	}
	
	
	/**
	 * <p>
	 * Envia uma mensagem ao seu respectivo destinatario
	 * </p>
	 * @param msg
	 */
	public void enviarMensagem(Mensagem msg) {
		// enviar salva no banco
		this.repositorio.save(msg);
		// realizao envio especifico
		
	}
}
