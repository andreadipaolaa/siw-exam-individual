package it.uniroma3.siw.siwexamindividual.service;


import it.uniroma3.siw.siwexamindividual.model.Buffet;
import it.uniroma3.siw.siwexamindividual.model.Piatto;
import it.uniroma3.siw.siwexamindividual.repository.BuffetRepository;
import it.uniroma3.siw.siwexamindividual.repository.PiattoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PiattoService {

    @Autowired
    private PiattoRepository piattoRepository;

    @Transactional
    public Piatto inserisci(Piatto piatto) {
        return piattoRepository.save(piatto);
    }


    public List<Piatto> tutti() {
        return (List<Piatto>) piattoRepository.findAll();
    }



    public boolean alreadyExists(Piatto piatto) {
        List<Piatto> elencoPiattiByNome = this.piattoRepository.findByNome(piatto.getNome());
        if (elencoPiattiByNome.size() > 0)
            return true;
        else
            return false;
    }

    @Transactional
    public void deleteById(Long id){
        this.piattoRepository.deleteById(id);
        return;
    }


    public Piatto getPiattoById(Long id){
        Optional<Piatto> result= this.piattoRepository.findById(id);
        return result.orElse(null);
    }

}

