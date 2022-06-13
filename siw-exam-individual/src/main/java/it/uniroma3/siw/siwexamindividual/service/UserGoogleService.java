package it.uniroma3.siw.siwexamindividual.service;

import it.uniroma3.siw.siwexamindividual.model.UserGoogle;
import it.uniroma3.siw.siwexamindividual.repository.UserGoogleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserGoogleService {
    @Autowired
    protected UserGoogleRepository userGoogleRepository;

    @Transactional
    public UserGoogle getUserGoogle(String email) {
        Optional<UserGoogle> result = this.userGoogleRepository.findByEmail(email);
        return result.orElse(null);
    }

    @Transactional
    public UserGoogle getUserGoogle(Long id) {
        Optional<UserGoogle> result = this.userGoogleRepository.findById(id);
        return result.orElse(null);
    }

    @Transactional
    public UserGoogle saveUserGoogle(UserGoogle userGoogle) {
        return this.userGoogleRepository.save(userGoogle);
    }

    @Transactional
    public List<UserGoogle> getAllGoogleUsers() {
        List<UserGoogle> result =(List<UserGoogle>) this.userGoogleRepository.findAll();
        return result;
    }
}
