package it.uniroma3.siw.siwexamindividual.controller;

import it.uniroma3.siw.siwexamindividual.model.Ingrediente;
import it.uniroma3.siw.siwexamindividual.service.IngredienteService;
import it.uniroma3.siw.siwexamindividual.validator.IngredienteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IngredienteController {
    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private IngredienteValidator ingredienteValidator;

    @GetMapping(value = "/admin/ingrediente")
    public String addIngrediente(Model model){
        model.addAttribute("ingrediente", new Ingrediente());
        return "ingredienteForm";
    }

    @PostMapping(value = "/admin/ingrediente")
    public String addIngrediente(Model model, @ModelAttribute("infrediente") Ingrediente ingrediente, BindingResult bindingResult){
        this.ingredienteValidator.validate(ingrediente, bindingResult);
        if(!bindingResult.hasErrors()){
            this.ingredienteService.inserisci(ingrediente);
            return "inserimentoRiuscito";
        }
        else
            return "ingredienteForm";
    }
}
