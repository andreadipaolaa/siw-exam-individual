package it.uniroma3.siw.siwexamindividual.repository;

import it.uniroma3.siw.siwexamindividual.model.Buffet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuffetRepository extends CrudRepository<Buffet, Long> {
    public List<Buffet> findByNome(String nome);
}