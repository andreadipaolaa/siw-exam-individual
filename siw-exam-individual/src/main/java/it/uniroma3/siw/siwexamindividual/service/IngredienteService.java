package it.uniroma3.siw.siwexamindividual.service;

import it.uniroma3.siw.siwexamindividual.model.Buffet;
import it.uniroma3.siw.siwexamindividual.model.Ingrediente;
import it.uniroma3.siw.siwexamindividual.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
        if(target.getId()!=null)
            return false;
        if (elencoIngredientiByNome.size() > 0)
            return true;
        else
            return false;
    }

    public Ingrediente getIngredienteById(Long id){
        Optional<Ingrediente> result= this.ingredienteRepository.findById(id);
        return result.orElse(null);
    }
    @Transactional
    public void deleteById(Long id){
        this.ingredienteRepository.deleteById(id);
        return;
    }
}
