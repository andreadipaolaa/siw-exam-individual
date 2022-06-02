package it.uniroma3.siw.service;

import it.uniroma3.siw.repository.BuffetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuffetService {

    @Autowired
    private BuffetRepository buffetRepository;

}
