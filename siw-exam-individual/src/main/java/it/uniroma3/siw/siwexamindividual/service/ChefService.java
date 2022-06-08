package it.uniroma3.siw.siwexamindividual.service;

import it.uniroma3.siw.siwexamindividual.model.Chef;
import it.uniroma3.siw.siwexamindividual.model.Ingrediente;
import it.uniroma3.siw.siwexamindividual.repository.ChefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ChefService {

    @Autowired
    private ChefRepository chefRepository;
    @Transactional
    public Chef inserisci(Chef chef){
        return this.chefRepository.save(chef);
    }

    public List<Chef> tutti(){
        return (List<Chef>) this.chefRepository.findAll();
    }

    public Chef getChefById(Long id){
        Optional<Chef> result= chefRepository.findById(id);
        return result.orElse(null);
    }

    public boolean alreadyExists(Chef target) {
        List<Chef> elencoChefByNome = this.chefRepository.findByNome(target.getNome());
        if (elencoChefByNome.size() > 0)
            return true;
        else
            return false;
    }
    @Transactional
    public void deleteById(Long id) {
        this.chefRepository.deleteById(id);
        return;
    }
}
