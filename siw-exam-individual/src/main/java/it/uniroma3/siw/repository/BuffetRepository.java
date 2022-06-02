package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Buffet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuffetRepository extends CrudRepository<Buffet, Long> {
    public List<Buffet> findByNome(String nome);
}