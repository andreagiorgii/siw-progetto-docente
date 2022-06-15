package it.uniroma3.siw.catering.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.model.User;

/**
 * Validator Buffet
 */
@Component
public class ChefValidator implements Validator {

	@Override
	public void validate(Object o, Errors errors) {
		Chef chef = (Chef) o;
		String nome = chef.getNome().trim();
		String cognome = chef.getCognome().trim();
		String nazionalita = chef.getNazionalita().trim();

		if (nome.matches(".*\\d.*"))
			errors.rejectValue("nome", "isNumber");

		if (cognome.matches(".*\\d.*"))
			errors.rejectValue("cognome", "isNumber");
		
		if (nazionalita.matches(".*\\d.*"))
			errors.rejectValue("nazionalita", "isNumber");
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

}
