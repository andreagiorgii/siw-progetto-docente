package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.respository.PiattoRepository;

@Service
public class PiattoService {

	@Autowired
	private PiattoRepository piattoRepository;

	/**
	 * Aggiungi un nuovo piatto
	 * 
	 * @param piatto
	 */
	@Transactional
	public void aggiungiPiatto(Piatto piatto) {
		piattoRepository.save(piatto);
	}

	@Transactional
	public void aggiornaPiatto(Piatto piatto) {
		piattoRepository.save(piatto);
	}

	@Transactional
	public List<Piatto> getPiatti() {
		List<Piatto> piatti = new ArrayList<Piatto>();
		for (Piatto p : piattoRepository.findAll()) {
			piatti.add(p);
		}
		return piatti;
	}

	/**
	 * Ritorna lo chef con id corrispondente
	 * 
	 * @param id
	 * @return
	 */
	public Piatto findById(@Valid Long id) {
		return piattoRepository.findById(id).get();
	}
	

	/**
	 * Ritorna lista dei piatti in base {buffet_id}
	 * 
	 * @param id
	 * @return
	 */
	public List<Piatto> getPiattoByBuffetId(@Valid Long id) {
		List<Piatto> piattiBuffet = new ArrayList<Piatto>();

		for (Piatto p : piattoRepository.findByBuffet_Id(id)) {
			piattiBuffet.add(p);
		}
		return piattiBuffet;
	}

}
