package it.uniroma3.siw.catering.respository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.model.Piatto;

public interface IngredienteRepository extends CrudRepository<Ingrediente, Long> {
	
	 List<Ingrediente> findByPiatto_Id(Long id);
}
