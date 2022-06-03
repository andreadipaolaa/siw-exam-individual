package it.uniroma3.siw.siwexamindividual.controller;

import it.uniroma3.siw.siwexamindividual.model.Buffet;
import it.uniroma3.siw.siwexamindividual.model.Chef;
import it.uniroma3.siw.siwexamindividual.service.BuffetService;
import it.uniroma3.siw.siwexamindividual.service.ChefService;
import it.uniroma3.siw.siwexamindividual.validator.BuffetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BuffetController {

    @Autowired
    private BuffetService buffetService;
    @Autowired
    private BuffetValidator buffetValidator;

    @Autowired
    private ChefService chefService;

    @GetMapping(value = "/admin/buffet")
    public String addBuffet(Model model){
        model.addAttribute("buffet", new Buffet());
        List<Chef> elencoChef= this.chefService.tutti();
        model.addAttribute("elencoChef", elencoChef);
        return "buffetForm";
    }

    @PostMapping(value = "/admin/buffet")
    public String addBuffet(Model model , @ModelAttribute("buffet") Buffet buffet , BindingResult bindingResult){
        this.buffetValidator.validate(buffet, bindingResult);
        if(!bindingResult.hasErrors()){
            this.buffetService.inserisci(buffet);
            model.addAttribute(buffet);
            return "elencoBuffet";
        }
        else
            return "buffetForm";

    }


}
