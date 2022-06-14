package it.uniroma3.siw.siwexamindividual.repository;

import it.uniroma3.siw.siwexamindividual.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);
}