package it.uniroma3.siw.siwexamindividual.validator;

import it.uniroma3.siw.siwexamindividual.model.Chef;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ChefValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Chef.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}