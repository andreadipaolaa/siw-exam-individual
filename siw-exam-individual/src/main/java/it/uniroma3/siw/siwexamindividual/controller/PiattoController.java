package it.uniroma3.siw.siwexamindividual.controller;

import it.uniroma3.siw.siwexamindividual.model.*;
import it.uniroma3.siw.siwexamindividual.service.CredentialsService;
import it.uniroma3.siw.siwexamindividual.service.IngredienteService;
import it.uniroma3.siw.siwexamindividual.service.PiattoService;
import it.uniroma3.siw.siwexamindividual.validator.PiattoValidator;
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
public class PiattoController {

    @Autowired
    private PiattoService piattoService;

    @Autowired
    private PiattoValidator piattoValidator;

    @Autowired
    private IngredienteService ingredienteService;


    @GetMapping(value = "/admin/piatto")
    public String addPiatto(Model model){
        model.addAttribute("piatto", new Piatto());
        List<Ingrediente> elencoIngredienti= this.ingredienteService.tutti();
        model.addAttribute("elencoIngredienti", elencoIngredienti);
        return "piattoForm";
    }

    @PostMapping(value = "/admin/piatto")
    public String addPiatto(Model model , @ModelAttribute("piatto") Piatto piatto , BindingResult bindingResult){
        this.piattoValidator.validate(piatto, bindingResult);
        if(!bindingResult.hasErrors()){
            this.piattoService.inserisci(piatto);
            for(Ingrediente ingrediente : piatto.getIngredienti())
                ingrediente.getPiatti().add(piatto);
            model.addAttribute("elencoPiatti", this.piattoService.tutti());
            return "elencoPiattiAdmin";
        }
        else{
            List<Ingrediente> elencoIngredienti= this.ingredienteService.tutti();
            model.addAttribute("elencoIngredienti", elencoIngredienti);
            return "piattoForm";
        }

    }

    @GetMapping(value = "/admin/elencoPiatti")
    public String mostraElencoPiattiAdmin(Model model){
        List<Piatto> elencoPiatti= piattoService.tutti();
        model.addAttribute("elencoPiatti", elencoPiatti);
        return "elencoPiattiAdmin";
    }

    @GetMapping(value = "/admin/piatto/{id}")
    public String dettagliPiattoAdmin(Model model, @PathVariable("id") Long id){
        model.addAttribute("piatto", piattoService.getPiattoById(id));
        return "piattoAdmin";
    }

    @GetMapping(value = "/elencoPiatti")
    public String mostraElencoPiatti(Model model){
        List<Piatto> elencoPiatti= piattoService.tutti();
        model.addAttribute("elencoPiatti", elencoPiatti);
        return "elencoPiatti";
    }

    @GetMapping(value = "/piatto/{id}")
    public String dettagliPiatto(Model model, @PathVariable("id") Long id){
        model.addAttribute("piatto", piattoService.getPiattoById(id));
        return "piatto";
    }

    @GetMapping(value = "/admin/deletePiatto/{id}")
    public String deletePiatto(Model model, @PathVariable("id") Long id){
        model.addAttribute("piatto", piattoService.getPiattoById(id));
        return "confermaDeletePiatto";
    }

    @PostMapping(value = "/admin/deletePiatto/{id}")
    public String ConfirmDeletePiatto(Model model,@PathVariable("id") Long id){
        Piatto piatto = this.piattoService.getPiattoById(id);
        for(Ingrediente ingrediente : piatto.getIngredienti())
            ingrediente.getPiatti().remove(piatto);
        for(Buffet buffet : piatto.getBuffets())
            buffet.getPiatti().remove(piatto);
        piattoService.deleteById(id);
        List<Piatto> elencoPiatti= piattoService.tutti();
        model.addAttribute("elencoPiatti", elencoPiatti);
        return "elencoPiattiAdmin";
    }

    @GetMapping(value = "/admin/modificaPiatto/{id}")
    public String updatePiatto(Model model, @PathVariable("id") Long id){
        Piatto piattoDaModificare= this.piattoService.getPiattoById(id);
        for(Ingrediente ingrediente : piattoDaModificare.getIngredienti())
            ingrediente.getPiatti().remove(piattoDaModificare);
        piattoDaModificare.getIngredienti().removeAll(piattoDaModificare.getIngredienti());
        model.addAttribute("piatto", piattoDaModificare);
        List<Ingrediente> elencoIngredienti= this.ingredienteService.tutti();
        model.addAttribute("elencoIngredienti", elencoIngredienti);
        return "piattoForm";
    }
}
