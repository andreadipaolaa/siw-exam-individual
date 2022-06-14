package it.uniroma3.siw.siwexamindividual.controller;

import it.uniroma3.siw.siwexamindividual.model.Buffet;
import it.uniroma3.siw.siwexamindividual.model.Chef;
import it.uniroma3.siw.siwexamindividual.model.Credentials;
import it.uniroma3.siw.siwexamindividual.model.Piatto;
import it.uniroma3.siw.siwexamindividual.service.BuffetService;
import it.uniroma3.siw.siwexamindividual.service.ChefService;
import it.uniroma3.siw.siwexamindividual.service.CredentialsService;
import it.uniroma3.siw.siwexamindividual.validator.ChefValidator;
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

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChefController {
    @Autowired
    private ChefService chefService;
    @Autowired
    private ChefValidator chefValidator;


    @GetMapping(value = "/admin/chef")
    public String addChef(Model model){
        model.addAttribute("chef", new Chef());
        return "chefForm";
    }

    @PostMapping(value = "/admin/chef")
    public String addChef(Model model, @ModelAttribute("chef") Chef chef, BindingResult bindingResult){
        this.chefValidator.validate(chef, bindingResult);
        if(!bindingResult.hasErrors()){
            chef.setBuffetOfferti(new ArrayList<>());
            this.chefService.inserisci(chef);
            model.addAttribute("elencoChef", this.chefService.tutti());
            return "/admin/elencoChef";
        }
        else
            return "chefForm";
    }

    @GetMapping(value = "/admin/elencoChef")
    public String mostraElencoChefAdmin(Model model){
        List<Chef> elencoChef= chefService.tutti();
        model.addAttribute("elencoChef", elencoChef);
        return "/admin/elencoChef";
    }

    @GetMapping(value = "/admin/chef/{id}")
    public String dettagliChefAdmin(Model model, @PathVariable("id") Long id){
        model.addAttribute("chef", chefService.getChefById(id));
        return "/admin/chef";
    }

    @GetMapping(value = "/elencoChef")
    public String mostraElencoChef(Model model){
        List<Chef> elencoChef= chefService.tutti();
        model.addAttribute("elencoChef", elencoChef);
        return "elencoChef";
    }

    @GetMapping(value = "/chef/{id}")
    public String dettagliChef(Model model, @PathVariable("id") Long id){
        model.addAttribute("chef", chefService.getChefById(id));
        return "chef";
    }

    @GetMapping(value = "/admin/deleteChef/{id}")
    public String deleteChef(Model model, @PathVariable("id") Long id){
        model.addAttribute("chef", this.chefService.getChefById(id));
        return "confermaDeleteChef";
    }

    @PostMapping(value = "/admin/deleteChef/{id}")
    public String ConfirmDeleteChef(Model model,@PathVariable("id") Long id){
        Chef chef = this.chefService.getChefById(id);
        for(Buffet buffet : chef.getBuffetOfferti())
            for(Piatto piatto : buffet.getPiatti())
                piatto.getBuffets().remove(buffet);
        this.chefService.deleteById(id);
        List<Chef> elencoChef= chefService.tutti();
        model.addAttribute("elencoChef", elencoChef);
        return "/admin/elencoChef";
    }
}
