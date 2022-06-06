package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.respository.IngredienteRepository;

@Service
public class IngredienteService {
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	/**
	 * Aggiungi un nuovo ingrediente
	 * @param piatto
	 */
	@Transactional
	public void aggiungiIngrediente(Ingrediente ingrediente) {
		ingredienteRepository.save(ingrediente);
	}
	

	
	/**
	 * Ritorna lista dei piatti in base {buffet_id}
	 * @param id
	 * @return
	 */
	public List<Ingrediente> findIngredienteByPiattoId(@Valid Long id){
		List<Ingrediente> ingredientiPiatto = new ArrayList<Ingrediente>();
		for(Ingrediente i : ingredienteRepository.findByPiatto_Id(id))
			ingredientiPiatto.add(i);
		return ingredientiPiatto;
	}
	
	
}
