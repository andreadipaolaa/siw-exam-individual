package it.uniroma3.siw.siwexamindividual.service;

import it.uniroma3.siw.siwexamindividual.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;
}
