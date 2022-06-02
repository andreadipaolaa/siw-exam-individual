package it.uniroma3.siw.service;


import it.uniroma3.siw.repository.PiattoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PiattoService {

    @Autowired
    private PiattoRepository piattoRepository;
}

