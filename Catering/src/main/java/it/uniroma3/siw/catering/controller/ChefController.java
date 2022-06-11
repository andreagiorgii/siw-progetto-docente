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

import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.service.ChefService;

@Controller
public class ChefController {

	@Autowired
	private ChefService chefService;

	// Aggiunge un nuovo chef dalla form
	@PostMapping("admin/chef/add")
	public String addChef(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			chefService.aggiungiChef(chef);
			model.addAttribute("chef", chef);
			return "adminDashboard.html";
		}
		return "chefForm";
	}

	// Visualizza form dopo aver inserito chef
	@GetMapping("/admin/chef/add")
	public String getChef(Model model) {
		model.addAttribute("chef", new Chef());
		return "chefForm";
	}

	// Visualizza pagina con lista di chef
	@GetMapping("/chef")
	public String getChefs(Model model) {
		List<Chef> chefs = chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "chefs";
	}

	// Visualizza pagina con singolo chef
	@GetMapping("/chef/{id}")
	public String getChefDetail(@PathVariable("id") Long id, Model model) {
		Chef chef = chefService.findById(id);
		model.addAttribute("chef", chef);
		return "chef";
	}

}
