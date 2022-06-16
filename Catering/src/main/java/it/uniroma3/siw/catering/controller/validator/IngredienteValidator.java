package it.uniroma3.siw.catering.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.model.User;

/**
 * Validator Buffet
 */
@Component
public class IngredienteValidator implements Validator {

	@Override
	public void validate(Object o, Errors errors) {
		Ingrediente ingrediente = (Ingrediente) o;
		String nome = ingrediente.getNome().trim();
		String descrizione = ingrediente.getDescrizione().trim();
		String origine = ingrediente.getOrigine().trim();

		if (nome.matches(".*\\d.*"))
			errors.rejectValue("nome", "isNumber");

		if (descrizione.matches(".*\\d.*"))
			errors.rejectValue("descrizione", "isNumber");

		if (origine.matches(".*\\d.*"))
			errors.rejectValue("descrizione", "isNumber");
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

}
