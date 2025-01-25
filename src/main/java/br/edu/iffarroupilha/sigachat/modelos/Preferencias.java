package br.edu.iffarroupilha.sigachat.modelos;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * <p>
 * Entidade que armazena as preferencias do usuario
 * </p>
 * 
 * @author Professor
 * @since Nov 6, 2024 8:16:49 PM
 */

@Entity
public class Preferencias implements IEntidade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPreferencias;
	@Column(nullable = false)
	// armazena a prefe xrencia, ex. tamanho_fonte
	private String tipoPreferencia;
	@Column(nullable = false)
	// armaze o valor ex. 16
	private String valor;

	public long getIdPreferencias() {
		return idPreferencias;
	}

	public void setIdPreferencias(long idPreferencias) {
		this.idPreferencias = idPreferencias;
	}

	public String getTipoPreferencia() {
		return tipoPreferencia;
	}

	public void setTipoPreferencia(String tipoPreferencia) {
		this.tipoPreferencia = tipoPreferencia;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
