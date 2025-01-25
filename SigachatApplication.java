package br.edu.iffarroupilha.sigachat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.iffarroupilha.sigachat.modelos.Usuario;

@SpringBootApplication
public class SigachatApplication {
	public static void main(String[] args) {
		SpringApplication.run(SigachatApplication.class, args);
	}
}