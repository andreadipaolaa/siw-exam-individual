package it.uniroma3.siw.siwexamindividual.validator;

import it.uniroma3.siw.siwexamindividual.model.Buffet;
import it.uniroma3.siw.siwexamindividual.model.Ingrediente;
import it.uniroma3.siw.siwexamindividual.service.IngredienteService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class IngredienteValidator implements Validator {
    private IngredienteService ingredienteService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Ingrediente.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            if (this.ingredienteService.alreadyExists((Ingrediente) target))
                errors.reject("duplicato");
        }
    }
}