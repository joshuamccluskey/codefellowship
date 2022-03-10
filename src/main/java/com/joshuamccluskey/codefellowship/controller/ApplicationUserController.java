package com.joshuamccluskey.codefellowship.controller;

import com.joshuamccluskey.codefellowship.model.ApplicationUser;
import com.joshuamccluskey.codefellowship.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDate;

@Controller
public class ApplicationUserController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/login")
    public String getLoginPage(){
        return "login.html";
    }


    @GetMapping("/signup")
    public String thisIsForTheCrerateAccountPage(){
        return "signup.html";
    }


    @GetMapping("/")
    public String thisIsForTheMainPage(Principal p, Model m){
        if (p != null){
            String username = p.getName();
            ApplicationUser newUser = applicationUserRepository.findByUsername(username);
            m.addAttribute("username", username);
        }
        return "index.html";
    }

    @PostMapping("/signup")
    public RedirectView creatingAUserAccount(String username, String password, String firstName, String lastName, @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dateofBirth, String bio){
        ApplicationUser newUser = new ApplicationUser();
        newUser.setUsername(username);
        String hashedPassword = passwordEncoder.encode(password);
        newUser.setPassword(hashedPassword);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setBio(bio);
        applicationUserRepository.save(newUser);
        authWithHttpServletRequest(username, password);
        return new RedirectView("/");
    }

    public void authWithHttpServletRequest(String username, String password){
        try{
            request.login(username, password);
        } catch (ServletException se){
            System.out.println("Yo an Error happened");
            se.printStackTrace();
        }
    }
}
