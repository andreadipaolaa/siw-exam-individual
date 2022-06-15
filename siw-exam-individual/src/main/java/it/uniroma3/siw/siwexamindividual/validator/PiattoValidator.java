package it.uniroma3.siw.siwexamindividual.validator;

import it.uniroma3.siw.siwexamindividual.model.Buffet;
import it.uniroma3.siw.siwexamindividual.model.Piatto;
import it.uniroma3.siw.siwexamindividual.service.PiattoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PiattoValidator implements Validator {
    @Autowired
    private PiattoService piattoService;
    @Override
    public boolean supports(Class<?> clazz) {
        return Piatto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(!errors.hasErrors()){
            if (this.piattoService.alreadyExists((Piatto) target))
                errors.reject("piatto.duplicato");
        }
    }
}