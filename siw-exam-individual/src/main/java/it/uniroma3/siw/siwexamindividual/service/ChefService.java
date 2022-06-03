package it.uniroma3.siw.siwexamindividual.service;

import it.uniroma3.siw.siwexamindividual.model.Chef;
import it.uniroma3.siw.siwexamindividual.repository.ChefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChefService {

    @Autowired
    private ChefRepository chefRepository;

    public Chef inserisci(Chef chef){
        return this.chefRepository.save(chef);
    }

    public List<Chef> tutti(){
        return (List<Chef>) this.chefRepository.findAll();
    }
}
