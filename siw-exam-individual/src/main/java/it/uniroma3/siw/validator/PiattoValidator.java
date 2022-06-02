package it.uniroma3.siw.validator;

import it.uniroma3.siw.model.Piatto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PiattoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Piatto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}