package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.respository.BuffetRepository;

@Service
public class BuffetService {

	@Autowired
	private BuffetRepository buffetRepository;

	/**
	 * Salva il buffet creato
	 * 
	 * @param buffet
	 */
	@Transactional
	public void aggiungiBuffet(Buffet buffet) {
		buffetRepository.save(buffet);
	}

	/**
	 * Ritorna lo chef con id corrispondente
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public Buffet findById(@Valid Long id) {
		return buffetRepository.findById(id).get();
	}

	/**
	 * Trova i buffet per chef_id
	 * 
	 * @param id
	 * @return
	 */
	public List<Buffet> findByChef(@Valid Long id) {
		List<Buffet> chefBuffet = new ArrayList<Buffet>();
		for (Buffet b : buffetRepository.findByChef_Id(id))
			chefBuffet.add(b);
		return chefBuffet;
	}

	/**
	 * Cancella il buffet in base all' {id}
	 * 
	 * @param id
	 */
	@Transactional
	public void deleteBuffet(Long id) {
		buffetRepository.deleteById(id);
		;
	}

	/**
	 * Restituisce una lista di buffet
	 * 
	 * @return
	 */
	public List<Buffet> findAll() {
		List<Buffet> buffets = new ArrayList<Buffet>();
		for (Buffet b : buffetRepository.findAll())
			buffets.add(b);
		return buffets;
	}

}
