package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.respository.ChefRepository;

@Service
public class ChefService {
	@Autowired
	private ChefRepository chefRepository;

	/**
	 * Salva lo chef - operazione transazionale (apre/chiude)
	 * 
	 * @param chef
	 * @return
	 */
	@Transactional
	public void aggiungiChef(Chef chef) {
		this.chefRepository.save(chef);
	}

	/**
	 * Ritorna lo chef con id corrispondente
	 * 
	 * @param id
	 * @return
	 */
	public Chef findById(@Valid Long id) {
		return this.chefRepository.findById(id).get();
	}
	
	@Transactional
	public Chef findDistinctById(@Valid Long id){
		return chefRepository.findDistinctById(id);
	}

	/**
	 * Restituisce una lista di chef
	 * 
	 * @return
	 */
	public List<Chef> findAll() {
		List<Chef> chefs = new ArrayList<Chef>();
		for (Chef c : chefRepository.findAll())
			chefs.add(c);
		return chefs;
	}

}
