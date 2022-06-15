package it.uniroma3.siw.siwexamindividual.validator;

import it.uniroma3.siw.siwexamindividual.model.Buffet;
import it.uniroma3.siw.siwexamindividual.service.BuffetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BuffetValidator implements Validator {

    @Autowired
    private BuffetService buffetService;
    @Override
    public boolean supports(Class<?> clazz) {
        return Buffet.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Buffet buffet= (Buffet) target;
        if(!errors.hasErrors()){
            if(buffet.getChef()==null) {
                errors.reject("buffet.chef.obbligatorio");
            }
            if (this.buffetService.alreadyExists(buffet))
                errors.reject("buffet.duplicato");
        }
    }
}
