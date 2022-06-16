package it.uniroma3.siw.catering.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.User;

/**
 * Validator Buffet
 */
@Component
public class BuffetValidator implements Validator {

	@Override
	public void validate(Object o, Errors errors) {
		Buffet buffet = (Buffet) o;
		String nome = buffet.getNome().trim();
		String descrizione = buffet.getDescrizione().trim();

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
