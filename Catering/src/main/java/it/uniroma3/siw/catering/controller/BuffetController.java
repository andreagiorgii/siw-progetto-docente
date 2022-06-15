package it.uniroma3.siw.catering.controller;

import java.util.ArrayList;
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

import it.uniroma3.siw.catering.controller.validator.BuffetValidator;
import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.ChefService;

@Controller
public class BuffetController {

	@Autowired
	private BuffetService buffetService;

	@Autowired
	private ChefService chefService;
	
	@Autowired
	private BuffetValidator buffetValidator;


	@PostMapping("/admin/buffet/add")
	public String addBuffet(@Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResult, Model model) {

		// validate fields
		this.buffetValidator.validate(buffet, bindingResult);
		
		if (!bindingResult.hasErrors()) {
			buffetService.aggiungiBuffet(buffet);
			model.addAttribute(buffet);
			return "adminDashboard";
		}
		return "buffetForm";
	}

	/**
	 * Ritorna un nuovo buffet e richiede gli chefs disponibili, da associare al
	 * buffet creato
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/buffet/add")
	public String getBuffet(Model model) {
		List<Buffet> buffets = buffetService.findAll();
		List<Chef> buffetChefs = chefService.findAll();
		model.addAttribute("buffets", buffets);
		model.addAttribute("buffetChefs", buffetChefs);
		model.addAttribute("buffet", new Buffet());
		return "buffetForm";
	}

	/**
	 * Ritorna tutti i buffet registrati
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/buffet")
	public String getBuffets(Model model) {
		List<Buffet> buffets = buffetService.findAll();
		List<Chef> chefs = new ArrayList<Chef>();
		for (Buffet b : buffets) {
			chefs.add(chefService.findDistinctById(b.getChef().getId()));
		}
		model.addAttribute("chefs", chefs);
		model.addAttribute("buffets", buffets);
		return "buffets";
	}

	@PostMapping("/admin/buffet/{id}/delete")
	public String deleteBuffet(@PathVariable("id") Long id) {
		buffetService.deleteBuffet(id);
		return "adminDashboard";
	}

	/**
	 * Ritorna i buffet dello chef con {id}
	 */
	@GetMapping("/chef/{id}/buffet")
	public String getBuffets(@PathVariable("id") Long id, Model model) {
		Chef chef = chefService.findById(id);
		List<Buffet> chefBuffets = buffetService.findByChef(id);
		model.addAttribute("chef", chef);
		model.addAttribute("chefBuffets", chefBuffets);
		return "chefBuffet";
	}

}
