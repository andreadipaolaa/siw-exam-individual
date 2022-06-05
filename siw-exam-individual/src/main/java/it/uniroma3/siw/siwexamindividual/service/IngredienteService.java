package it.uniroma3.siw.siwexamindividual.service;

import it.uniroma3.siw.siwexamindividual.model.Buffet;
import it.uniroma3.siw.siwexamindividual.model.Ingrediente;
import it.uniroma3.siw.siwexamindividual.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;
    @Transactional
    public Ingrediente inserisci(Ingrediente ingrediente){
        return this.ingredienteRepository.save(ingrediente);
    }

    public List<Ingrediente> tutti (){
        return (List<Ingrediente>) this.ingredienteRepository.findAll();
    }

    public boolean alreadyExists(Ingrediente target) {
        List<Ingrediente> elencoIngredientiByNome = this.ingredienteRepository.findByNome(target.getNome());
        if (elencoIngredientiByNome.size() > 0)
            return true;
        else
            return false;
    }
}
