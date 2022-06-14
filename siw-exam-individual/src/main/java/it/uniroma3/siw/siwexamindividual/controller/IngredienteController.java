package it.uniroma3.siw.siwexamindividual.controller;

import it.uniroma3.siw.siwexamindividual.model.Buffet;
import it.uniroma3.siw.siwexamindividual.model.Credentials;
import it.uniroma3.siw.siwexamindividual.model.Ingrediente;
import it.uniroma3.siw.siwexamindividual.service.CredentialsService;
import it.uniroma3.siw.siwexamindividual.service.IngredienteService;
import it.uniroma3.siw.siwexamindividual.validator.IngredienteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
    public String addIngrediente(Model model, @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResult){
        this.ingredienteValidator.validate(ingrediente, bindingResult);
        if(!bindingResult.hasErrors()){
            this.ingredienteService.inserisci(ingrediente);
            return "/admin/elencoIngredienti";
        }
        else
            return "ingredienteForm";
    }

    @GetMapping(value = "/admin/elencoIngredienti")
    public String mostraElencoIngredienti(Model model){
        List<Ingrediente> elencoIngredienti= ingredienteService.tutti();
        model.addAttribute("elencoIngredienti", elencoIngredienti);
        return "/admin/elencoIngredienti";
    }
    @GetMapping(value = "/admin/deleteIngrediente/{id}")
    public String deleteIngrediente(Model model, @PathVariable("id") Long id){
        model.addAttribute("ingrediente", ingredienteService.getIngredienteById(id));
        return "confermaDeleteIngrediente";
    }

    @PostMapping(value = "/admin/deleteIngrediente/{id}")
    public String ConfirmDeleteIngrediente(Model model,@PathVariable("id") Long id){
        ingredienteService.deleteById(id);
        List<Ingrediente> elencoIngredienti= ingredienteService.tutti();
        model.addAttribute("elencoIngredienti", elencoIngredienti);
        return "elencoIngredienti";
    }
}
