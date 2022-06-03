package it.uniroma3.siw.siwexamindividual.repository;

import it.uniroma3.siw.siwexamindividual.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}