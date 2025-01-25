package br.edu.iffarroupilha.sigachat.modelos;

import java.util.Date;

import br.edu.iffarroupilha.sigachat.modelos.dto.MensagemDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Mensagem implements IEntidade {
	
	
	public Mensagem() {
	
	}
	
	public Mensagem(MensagemDTO dto) {
		this.idMensagem = dto.idMensagem();
		this.conteudo = dto.conteudo();
		this.remetente = dto.remente();
		this.destinatario = dto.destinatario();
		this.grupoDestinatario = dto.grupoDestinatario();
		this.data = dto.data();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idMensagem;
	@Column(nullable = false,length = 500)
	private String conteudo;
	@ManyToOne
	@JoinColumn(name = "email_remetente", nullable = false)
	private Usuario remetente;
	
	@ManyToOne
	@JoinColumn(name = "email_destinatario")
	private Usuario destinatario;
	@ManyToOne
	@JoinColumn(name = "idGrupo")
	private Grupo grupoDestinatario;
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	
	public long getIdMensagem() {
		return idMensagem;
	}
	public void setIdMensagem(long idMensagem) {
		this.idMensagem = idMensagem;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public Usuario getRemetente() {
		return remetente;
	}
	public void setRemetente(Usuario remetente) {
		this.remetente = remetente;
	}
	public Usuario getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(Usuario destinatario) {
		this.destinatario = destinatario;
	}
	public Grupo getGrupoDestinatario() {
		return grupoDestinatario;
	}
	public void setGrupoDestinatario(Grupo grupoDestinatario) {
		this.grupoDestinatario = grupoDestinatario;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	
}
