package it.uniroma3.siw.catering.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.catering.controller.validator.PiattoValidator;
import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.ChefService;
import it.uniroma3.siw.catering.service.PiattoService;

@Controller
public class PiattoController {

	@Autowired
	private PiattoService piattoService;

	@Autowired
	private BuffetService buffetService;

	@Autowired
	private ChefService chefService;

	@Autowired
	private PiattoValidator piattoValidator;

	/* Ritorna il buffet associato e dispone nuova creazione piatto */
	@GetMapping("/admin/buffet/dish/add")
	public String getPiatto(Model model) {
		List<Buffet> buffets = buffetService.findAll();
		model.addAttribute("buffets", buffets);
		model.addAttribute("piatto", new Piatto());
		return "piattoForm";
	}

	/**
	 * Ritorna i piatti del buffet con {id}
	 */
	@GetMapping("/chef/{id_chef}/buffet/{id_buffet}/dishes")
	public String getPiatti(@PathVariable("id_buffet") Long id_buffet, @PathVariable("id_chef") Long id_chef,
			Model model) {
		Chef chef = chefService.findById(id_chef);
		Buffet buffet = buffetService.findById(id_buffet);
		List<Piatto> buffetDishes = piattoService.getPiattoByBuffetId(id_buffet);
		model.addAttribute("chef", chef);
		model.addAttribute("buffet", buffet);
		model.addAttribute("buffetDishes", buffetDishes);
		return "buffetDish";
	}

	/**
	 * Ritorna i piatti del buffet con {id}
	 */

	@GetMapping("/buffet/{id_buffet}/dishes")
	public String getPiatti(@PathVariable("id_buffet") Long id_buffet, Model model) {
		Buffet buffet = buffetService.findById(id_buffet);
		List<Piatto> buffetDishes = piattoService.getPiattoByBuffetId(id_buffet);
		Chef chef = chefService.findById(buffet.getChef().getId());
		model.addAttribute("chef", chef);
		model.addAttribute("buffet", buffet);
		model.addAttribute("buffetDishes", buffetDishes);
		return "buffetDish";
	}

	@GetMapping("/admin/dishes/update")
	public String getPiattiUpdate(Model model) {
		List<Piatto> piatti = piattoService.getPiatti();
		model.addAttribute("piatti", piatti);
		model.addAttribute("piatto", new Piatto());
		return "piatti";
	}

	@GetMapping("/admin/dishes/dish/{id}/update")
	public String getPiattoToUpdate(@PathVariable("id") Long id, Model model) {
		List<Buffet> buffets = buffetService.findAll();
		Piatto piatto = piattoService.findById(id);
		model.addAttribute("buffets", buffets);
		model.addAttribute("piatto", piatto);
		return "piattoUpdate";
	}

	@PostMapping("/admin/buffet/dish/{id}/update")
	public String updatePiatto(@Valid @ModelAttribute("piatto") Piatto piatto, BindingResult bindingResult,
			Model model) {
		piattoService.aggiornaPiatto(piatto);
		model.addAttribute("piatto", piatto);
		return "adminDashboard";
	}

	/**
	 * Aggiunge un nuovo piatto al buffet
	 * 
	 * @param piatto
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@PostMapping("/admin/buffet/dish/add")
	public String addPiatto(@Valid @ModelAttribute("piatto") Piatto piatto, BindingResult bindingResult, Model model) {

		this.piattoValidator.validate(piatto, bindingResult);
		
		if (!bindingResult.hasErrors()) {
			piattoService.aggiungiPiatto(piatto);
			model.addAttribute("piatto", piatto);
			return "adminDashboard";	
		} else {
			List<Buffet> buffets = buffetService.findAll();
			model.addAttribute("buffets", buffets);
			return "piattoForm";
		}

	}

}
