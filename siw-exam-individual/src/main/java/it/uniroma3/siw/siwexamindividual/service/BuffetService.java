package it.uniroma3.siw.siwexamindividual.service;

import it.uniroma3.siw.siwexamindividual.model.Buffet;
import it.uniroma3.siw.siwexamindividual.repository.BuffetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BuffetService {

    @Autowired
    private BuffetRepository buffetRepository;

    @Transactional
    public Buffet inserisci(Buffet buffet) {
        return buffetRepository.save(buffet);
    }

    @Transactional
    public List<Buffet> tutti() {
        return (List<Buffet>) buffetRepository.findAll();
    }


    @Transactional
    public boolean alreadyExists(Buffet buffet) {
        List<Buffet> elencoBuffetByNome = this.buffetRepository.findByNome(buffet.getNome());
        if(buffetRepository.existsById(buffet.getId()))
            return false;
        else if (elencoBuffetByNome.size() > 0)
            return true;
        else
            return false;
    }

    @Transactional
    public void deleteById(Long id){
        buffetRepository.deleteById(id);
        return;
    }

    @Transactional
    public Buffet getBuffetById(Long id){
        Optional<Buffet> result= buffetRepository.findById(id);
        return result.orElse(null);
    }

}
