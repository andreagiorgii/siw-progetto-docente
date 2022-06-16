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

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.service.IngredienteService;
import it.uniroma3.siw.catering.service.PiattoService;

@Controller
public class IngredienteController {

	@Autowired
	private IngredienteService ingredienteService;

	@Autowired
	private PiattoService piattoService;

	@GetMapping("/admin/buffet/dish/ingrediente/add")
	public String getIngrediente(Model model) {
		List<Piatto> piatti = piattoService.getPiatti();
		model.addAttribute("piatti", piatti);
		model.addAttribute("ingrediente", new Ingrediente());
		return "ingredienteForm";
	}

	/**
	 * Aggiunge ingrediente
	 * @param ingrediente
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@PostMapping("/admin/buffet/dish/ingrediente/add")
	public String addIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente,
			BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			ingredienteService.aggiungiIngrediente(ingrediente);
			model.addAttribute("ingrediente", ingrediente);
			return "adminDashboard";
		}
		List<Piatto> piatti = piattoService.getPiatti();
		model.addAttribute("piatti", piatti);
		return "ingredienteForm";
	}

	/**
	 * Ritorna ingrediente di ogni piatto
	 * @param id_buffet
	 * @param id_piatto
	 * @param model
	 * @return
	 */
	@GetMapping("/buffet/{id_buffet}/dish/{id_piatto}/ingredienti")
	public String getIngredienti(@PathVariable("id_buffet") Long id_buffet, @PathVariable("id_piatto") Long id_piatto,
			Model model) {
		Piatto piatto = piattoService.findById(id_piatto);
		List<Ingrediente> ingredienti = ingredienteService.findIngredienteByPiattoId(id_piatto);
		model.addAttribute("ingredienti", ingredienti);
		model.addAttribute("piatto", piatto);
		return "dishIngredienti";
	}

}
