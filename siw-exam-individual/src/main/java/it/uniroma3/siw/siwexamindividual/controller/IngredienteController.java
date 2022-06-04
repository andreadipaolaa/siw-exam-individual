package it.uniroma3.siw.siwexamindividual.controller;

import it.uniroma3.siw.siwexamindividual.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class IngredienteController {
    @Autowired
    private IngredienteService ingredienteService;

}
