package it.uniroma3.siw.catering.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.model.User;

/**
 * Validator Buffet
 */
@Component
public class PiattoValidator implements Validator {

	@Override
	public void validate(Object o, Errors errors) {
		Piatto piatto = (Piatto) o;
		String nome = piatto.getNome().trim();
		String descrizione = piatto.getDescrizione().trim();

		if (nome.matches(".*\\d.*"))
			errors.rejectValue("nome", "isNumber");

		if (descrizione.matches(".*\\d.*"))
			errors.rejectValue("descrizione", "isNumber");
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

}
