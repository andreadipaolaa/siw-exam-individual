package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Buffet;
import it.uniroma3.siw.repository.BuffetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BuffetService {

    @Autowired
    private BuffetRepository buffetRepository;

    @Transactional
    public Buffet inserisci(Buffet prodotto) {
        return buffetRepository.save(prodotto);
    }

    @Transactional
    public List<Buffet> tutti() {
        return (List<Buffet>) buffetRepository.findAll();
    }

    @Transactional
    public boolean alreadyExists(Buffet buffet) {
        List<Buffet> buffets = this.buffetRepository.findByNome(buffet.getNome());
        if (buffets.size() > 0)
            return true;
        else
            return false;
    }
}
