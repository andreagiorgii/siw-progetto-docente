package it.uniroma3.siw.catering.respository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.catering.model.Chef;

public interface ChefRepository extends CrudRepository<Chef, Long> {
	
	Chef findDistinctById(Long id);

}
