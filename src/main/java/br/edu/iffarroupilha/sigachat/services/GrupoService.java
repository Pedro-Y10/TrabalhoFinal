package br.edu.iffarroupilha.sigachat.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iffarroupilha.sigachat.modelos.Grupo;
import br.edu.iffarroupilha.sigachat.modelos.repositorios.GrupoRepositorio;

/**
 * <p>
 * Classe de serviços (regras de negócio) para o grupo
* </p>
* @author Professor
* @since Nov 6, 2024 7:36:00 PM
*/
@Service
public class GrupoService extends AService{
	private GrupoRepositorio repositorio;
	
	public GrupoService(GrupoRepositorio  repositorio) {
		super(repositorio);	
		this.repositorio = repositorio;
	}

	//Atualizar dados:
    public Grupo atualizar(Grupo grupo) {
        return repositorio.save(grupo);
    }
    
    //Deletar dados:
    public void apagar(Grupo grupo) {
        this.repositorio.delete(grupo);
    }
    
    //Buscar por ID:
    public Grupo buscarPorId(Long id) {
    	Optional<Grupo> grupo = repositorio.findById(id);
        return grupo.orElse(null);
    }
    
    //Listar dados:
    public List<Grupo> buscarTodos() {
        return this.repositorio.findAll();
    }
}
