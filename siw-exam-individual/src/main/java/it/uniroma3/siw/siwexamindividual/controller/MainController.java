package it.uniroma3.siw.siwexamindividual.controller;

import it.uniroma3.siw.siwexamindividual.model.Credentials;
import it.uniroma3.siw.siwexamindividual.model.User;
import it.uniroma3.siw.siwexamindividual.service.CredentialsService;
import it.uniroma3.siw.siwexamindividual.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }
}
