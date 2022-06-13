package it.uniroma3.siw.siwexamindividual.repository;

import it.uniroma3.siw.siwexamindividual.model.UserGoogle;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserGoogleRepository extends CrudRepository<UserGoogle, Long> {
    public Optional<UserGoogle> findByEmail(String email);
}