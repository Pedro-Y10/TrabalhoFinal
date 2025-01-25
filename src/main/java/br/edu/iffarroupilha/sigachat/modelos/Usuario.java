package br.edu.iffarroupilha.sigachat.modelos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.Collate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.edu.iffarroupilha.sigachat.modelos.dto.UsuarioDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * <p>
 * Classe de dominio da aplicacao, representa um usuario
 * </p>
 * 
 * @since 26/10/2024 20:24
 * @author Professor
 */
@Entity
public class Usuario implements IEntidade, UserDetails {
	@Id
	private String email;
	@Column(nullable = false)
	private String nome;
	@Column(nullable  = false)
	private String senha;
	private String foto;
	private String status;
	
	
	public Usuario() {
	}
	
	public Usuario(UsuarioDTO dto) {
		this.nome = dto.nome();
		this.email = dto.email();
		this.senha = dto.senha();
		this.status = dto.status();
		this.foto = dto.foto();
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List permissoes = new ArrayList();
		permissoes.add(new SimpleGrantedAuthority( "ROLE_USER"));
		//permissoes.add(new SimpleGrantedAuthority( "ROLE_ADMIN"));
		return permissoes;
	}
	
	public String getPassword() {
		return this.senha;
	}
	
	public String getUsername() {
		return this.email;
	}
}
