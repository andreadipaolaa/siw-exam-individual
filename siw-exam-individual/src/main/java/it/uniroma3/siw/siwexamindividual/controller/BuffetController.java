package it.uniroma3.siw.siwexamindividual.controller;

import it.uniroma3.siw.siwexamindividual.model.Buffet;
import it.uniroma3.siw.siwexamindividual.model.Chef;
import it.uniroma3.siw.siwexamindividual.model.Credentials;
import it.uniroma3.siw.siwexamindividual.model.Piatto;
import it.uniroma3.siw.siwexamindividual.service.BuffetService;
import it.uniroma3.siw.siwexamindividual.service.ChefService;
import it.uniroma3.siw.siwexamindividual.service.CredentialsService;
import it.uniroma3.siw.siwexamindividual.service.PiattoService;
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
import java.util.ArrayList;
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
    private PiattoService piattoService;

    @GetMapping(value = "/admin/buffet")
    public String addBuffet(Model model){
        model.addAttribute("buffet", new Buffet());
        List<Chef> elencoChef= this.chefService.tutti();
        model.addAttribute("elencoChef", elencoChef);
        List<Piatto> elencoPiatti= this.piattoService.tutti();
        model.addAttribute("elencoPiatti", elencoPiatti);
        return "buffetForm";
    }

    @PostMapping(value = "/admin/buffet")
    public String addBuffet(Model model , @ModelAttribute("buffet") Buffet buffet , BindingResult bindingResult){
        this.buffetValidator.validate(buffet, bindingResult);
        if(!bindingResult.hasErrors()){
            this.buffetService.inserisci(buffet);
            buffet.getChef().getBuffetOfferti().add(buffet);
            for( Piatto piatto : buffet.getPiatti())
                piatto.getBuffets().add(buffet);
            model.addAttribute("elencoBuffet", buffetService.tutti());
            return "/admin/elencoBuffet";
        }
        else{
            return "buffetForm";
        }


    }

    @GetMapping(value = "/admin/elencoBuffet")
    public String mostraElencoBuffetAdmin(Model model){
        List<Buffet> elencoBuffet= buffetService.tutti();
        model.addAttribute("elencoBuffet", elencoBuffet);
        return "/admin/elencoBuffet";
    }

    @GetMapping(value = "/admin/buffet/{id}")
    public String dettagliBuffetAdmin(Model model, @PathVariable("id") Long id){
        model.addAttribute("buffet", buffetService.getBuffetById(id));
        model.addAttribute("elencoPiatti", buffetService.getBuffetById(id).getPiatti());

        return "/admin/buffet";
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
        model.addAttribute("elencoPiatti", buffetService.getBuffetById(id).getPiatti());
        return "buffet";
    }

    @GetMapping(value = "/admin/deleteBuffet/{id}")
    public String deleteBuffet(Model model, @PathVariable("id") Long id){
        model.addAttribute("buffet", buffetService.getBuffetById(id));
        return "confermaDeleteBuffet";
    }

    @PostMapping(value = "/admin/deleteBuffet/{id}")
    public String ConfirmDeleteBuffet(Model model,@PathVariable("id") Long id){
        Buffet buffet = this.buffetService.getBuffetById(id);
        for(Piatto piatto : buffet.getPiatti())
            piatto.getBuffets().remove(buffet);
        buffetService.deleteById(id);
        List<Buffet> elencoBuffet= buffetService.tutti();
        model.addAttribute("elencoBuffet", elencoBuffet);
        return "/admin/elencoBuffet";
    }

    @GetMapping(value = "/admin/modificaBuffet/{id}")
    public String updateBuffet(Model model, @PathVariable("id") Long id){
        Buffet buffetDaModificare= this.buffetService.getBuffetById(id);
        buffetDaModificare.setPiatti(new ArrayList<>());
        model.addAttribute("buffet", buffetDaModificare);
        List<Chef> elencoChef= this.chefService.tutti();
        model.addAttribute("elencoChef", elencoChef);
        List<Piatto> elencoPiatti= this.piattoService.tutti();
        model.addAttribute("elencoPiatti", elencoPiatti);
        return "buffetForm";
    }



}
