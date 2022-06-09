package it.uniroma3.siw.siwexamindividual.repository;

import it.uniroma3.siw.siwexamindividual.model.Piatto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PiattoRepository extends CrudRepository<Piatto, Long> {
    List<Piatto> findByNome(String nome);
}