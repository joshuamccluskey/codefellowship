package com.joshuamccluskey.codefellowship.controller;

import com.joshuamccluskey.codefellowship.model.ApplicationUser;
import com.joshuamccluskey.codefellowship.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;

@Controller
public class ApplicationUserController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String getLogin () {
        return "index.html";
    }

    @GetMapping("/signup")
    public String getCreateAccount () {
        return "create-account.html";
    }

    @PostMapping("/signup")
    public RedirectView createNewUserAccount (String username,
    String password,
    String firstName,
    String lastName,
    LocalDate dateOfBirth,
    String bio
    ) {
        ApplicationUser newUser = new ApplicationUser();
        ApplicationUser.setUsername(username);
        String encryptedPassword = passwordEncoder.encode(password);
        ApplicationUser.setPassword(encryptedPassword);
        ApplicationUser.setFirstName(firstName);
        ApplicationUser.setLastName(lastName);
        ApplicationUser.setDateOfBirth(dateOfBirth);
        ApplicationUser.setBio(bio);

        applicationUserRepository.save(newUser);

        return new RedirectView("/");
    }
}
