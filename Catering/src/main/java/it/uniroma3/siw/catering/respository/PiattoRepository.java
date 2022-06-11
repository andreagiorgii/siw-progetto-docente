package it.uniroma3.siw.catering.respository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.catering.model.Piatto;

public interface PiattoRepository extends CrudRepository<Piatto, Long> {

	List<Piatto> findByBuffet_Id(Long id);
}
