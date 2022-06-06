package it.uniroma3.siw.catering.respository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;

public interface BuffetRepository extends CrudRepository<Buffet, Long> {
	
	 List<Buffet> findByChef_Id(Long id);

}
