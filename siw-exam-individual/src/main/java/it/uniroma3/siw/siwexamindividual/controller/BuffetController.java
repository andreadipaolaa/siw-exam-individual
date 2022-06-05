package it.uniroma3.siw.siwexamindividual.controller;

import it.uniroma3.siw.siwexamindividual.model.Buffet;
import it.uniroma3.siw.siwexamindividual.model.Chef;
import it.uniroma3.siw.siwexamindividual.model.Credentials;
import it.uniroma3.siw.siwexamindividual.service.BuffetService;
import it.uniroma3.siw.siwexamindividual.service.ChefService;
import it.uniroma3.siw.siwexamindividual.service.CredentialsService;
import it.uniroma3.siw.siwexamindividual.validator.BuffetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BuffetController {

    @Autowired
    private BuffetService buffetService;
    @Autowired
    private BuffetValidator buffetValidator;

    @Autowired
    private ChefService chefService;

    @Autowired
    private CredentialsService credentialsService;

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
            model.addAttribute("elencoBuffet", buffetService.tutti());
            return "elencoBuffet";
        }
        else
            return "buffetForm";

    }

    @GetMapping(value = "/elencoBuffet")
    public String mostraElencoBuffet(Model model){
        List<Buffet> elencoBuffet= buffetService.tutti();
        model.addAttribute("elencoBuffet", elencoBuffet);
        return "elencoBuffet";
    }

    @GetMapping(value = "/buffet/{id}")
    public String dettagliBuffet(Model model, @PathVariable("id") Long id){
        model.addAttribute("buffet", buffetService.getBuffetById(id));
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
        if(credentials.getRole().equals(Credentials.ADMIN_ROLE)){
            model.addAttribute("admin", true);
        }
        return "buffet";
    }

    @GetMapping(value = "/admin/deleteBuffet/{id}")
    public String deleteBuffet(Model model, @PathVariable("id") Long id){
        model.addAttribute("buffet", buffetService.getBuffetById(id));
        return "confermaDeleteBuffet";
    }

    @PostMapping(value = "/admin/deleteBuffet/{id}")
    public String ConfirmDeleteBuffet(Model model,@PathVariable("id") Long id){
        buffetService.deleteById(id);
        List<Buffet> elencoBuffet= buffetService.tutti();
        model.addAttribute("elencoBuffet", elencoBuffet);
        return "elencoBuffet";
    }

    @GetMapping(value = "/admin/modificaBuffet/{id}")
    public String updateBuffet(Model model, @PathVariable("id") Long id){
        model.addAttribute("buffet", buffetService.getBuffetById(id));
        List<Chef> elencoChef= this.chefService.tutti();
        model.addAttribute("elencoChef", elencoChef);
        return "buffetForm";
    }



}