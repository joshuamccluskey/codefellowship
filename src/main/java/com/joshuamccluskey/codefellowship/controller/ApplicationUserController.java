package com.joshuamccluskey.codefellowship.controller;

import com.joshuamccluskey.codefellowship.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationUserController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/login")
    public String getLogin () {
        return "index.html";
    }
}
