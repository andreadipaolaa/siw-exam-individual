package it.uniroma3.siw.siwexamindividual.controller;


import it.uniroma3.siw.siwexamindividual.service.CredentialsService;
import it.uniroma3.siw.siwexamindividual.service.UserService;
import it.uniroma3.siw.siwexamindividual.validator.CredentialsValidator;
import it.uniroma3.siw.siwexamindividual.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.siwexamindividual.model.Credentials;
import it.uniroma3.siw.siwexamindividual.model.User;

@Controller
public class AuthController {

    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private CredentialsValidator credentialsValidator;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterForm (Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("credentials", new Credentials());
        return "registerUser";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm (Model model) {
        return "loginForm";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        return "index";
    }

    @RequestMapping(value = "/default", method = RequestMethod.GET)
    public String defaultAfterLogin(Model model) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
        if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
            return "homeAdmin";
        }
        return "home";
    }

    @RequestMapping(value = { "/register" }, method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user,
                               BindingResult userBindingResult,
                               @ModelAttribute("credentials") Credentials credentials,
                               BindingResult credentialsBindingResult,
                               Model model) {

        // validate user and credentials fields
        this.userValidator.validate(user, userBindingResult);
        this.credentialsValidator.validate(credentials, credentialsBindingResult);

        // if neither of them had invalid contents, store the User and the Credentials into the DB
        if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
            // set the user and store the credentials;
            // this also stores the User, thanks to Cascade.ALL policy
            credentials.setUser(user);
            credentialsService.saveCredentials(credentials);
            return "registrationSuccessful";
        }
        return "registerUser";
    }

    @RequestMapping(value = "/defaultGoogle", method = RequestMethod.GET)
    public String defaultAfterLoginGoogle(Model model, @AuthenticationPrincipal OidcUser principal) {
        User userGoogle = this.userService.getUserByEmail(principal.getEmail());
        if (userGoogle == null) {
            saveGoogleUser(model, principal);
        }
        return "home";
    }

    @RequestMapping(value = "/saveGoogleUser", method = RequestMethod.POST)
    public String saveGoogleUser(Model model, OidcUser principal) {
        User user = this.userService.getUserByEmail(principal.getEmail());
        if (user == null) {
            user = new User();
            user.setEmail(principal.getEmail());
            user.setNome(principal.getName());
            user.setCognome((principal.getFamilyName()));
            Credentials credenziali= new Credentials();
            credenziali.setUser(user);
            credenziali.setUsername(principal.getEmail());
            credenziali.setPassword("passwordInutile");
            this.credentialsService.saveCredentials(credenziali);
        }
        return "home";
    }
}
