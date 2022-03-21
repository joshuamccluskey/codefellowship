package com.joshuamccluskey.codefellowship.controller;

import com.joshuamccluskey.codefellowship.model.ApplicationUser;
import com.joshuamccluskey.codefellowship.model.Post;
import com.joshuamccluskey.codefellowship.repository.ApplicationUserRepository;
import com.joshuamccluskey.codefellowship.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ApplicationUserController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/login")
    public String getLoginPage(){
        return ("login.html");
    }


    @GetMapping("/signup")
    public String getCreateAccountPage(){
        return ("signup.html");
    }

    @GetMapping("/home")
    public String getHomePage(Model m, Principal p){
        String username =  p.getName();
        ApplicationUser newUser = (ApplicationUser) applicationUserRepository.findByUsername(username);
        m.addAttribute("username", username);
        return ("home.html");
    }
    @GetMapping("/")
    public String thisIsForTheMainPage(Principal p, Model m){
        if (p != null){
            String username = p.getName();
            ApplicationUser newUser = (ApplicationUser) applicationUserRepository.findByUsername(username);
            m.addAttribute("username", username);
        }
        return ("splash.html");
    }

    @PostMapping("/signup")
    public RedirectView creatingAUserAccount(String username,
                                             String password,
                                             String firstName,
                                             String lastName,
                                             @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dateOfBirth,
                                             String bio){
        ApplicationUser newUser = new ApplicationUser();
        newUser.setUsername(username);
        String hashedPassword = passwordEncoder.encode(password);
        newUser.setPassword(hashedPassword);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setDateOfBirth(dateOfBirth);
        newUser.setBio(bio);
        applicationUserRepository.save(newUser);
        authWithHttpServletRequest(username, password);
        return new RedirectView("/");
    }

    @GetMapping("/myprofile")
    public String getMyProfilePage(Principal p, Model m){
        String username = p.getName();
        if(p != null) {
            ApplicationUser currentUser = (ApplicationUser) applicationUserRepository.findByUsername(username);
            ApplicationUser currentUserId = (ApplicationUser) applicationUserRepository.findByUsername(username);
            m.addAttribute("id", currentUserId);
            m.addAttribute("username", username);
            m.addAttribute("pic", currentUser.getPic());
            m.addAttribute("firstName", currentUser.getFirstName());
            m.addAttribute("lastName", currentUser.getLastName());
            m.addAttribute("dateOfBirth", currentUser.getDateOfBirth());
            m.addAttribute("bio", currentUser.getBio());
            m.addAttribute("currentUser", currentUser);
            m.addAttribute("posts", currentUser.getPostListByUser());
        }
        return ("/myprofile");
    }

    @GetMapping("/users/{id}")
    public String getMyProfilePage(@PathVariable long id, Model m){
        ApplicationUser currentUser = applicationUserRepository.findById(id);
        m.addAttribute("currentUserId",currentUser.getId());
        m.addAttribute("currentUsername",currentUser.getUsername());
        m.addAttribute("posts", currentUser.getPostListByUser());
        m.addAttribute("username", currentUser.getUsername());
        m.addAttribute("pic", currentUser.getPic());
        m.addAttribute("bio", currentUser.getBio());
        return ("users.html");
    }


    public void authWithHttpServletRequest(String username, String password){
        try{
            request.login(username, password);
        } catch (ServletException se){
            System.out.println("Yo an Error happened");
            se.printStackTrace();
        }
    }

    @PostMapping("/logout")
    public RedirectView logOutUserAndGetLogin(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        session.invalidate();

        return new RedirectView("/login");
    }

    @PostMapping("/addpost")
    public RedirectView addNewPost(Principal p, Model m, String title, String body) {
        if (p != null) {
            String username = p.getName();
            ApplicationUser applicationUser = (ApplicationUser) applicationUserRepository.findByUsername(username);
            m.addAttribute("applicationUser", applicationUser);
            Post post = new Post(title, body);
            post.setCreatedAt(new Date());
            post.setApplicationUser(applicationUser);
            postRepository.save(post);
        }
        return new RedirectView("/myprofile");
    }

    @GetMapping("/siteusers")
    public String getAllUsersPage(Principal p, Model m) {
        String username = p.getName();
        if (p != null) {
        ApplicationUser applicationUser = (ApplicationUser) applicationUserRepository.findByUsername(username);
            m.addAttribute("applicationUser", applicationUser);
        }
        ApplicationUser currentUser = (ApplicationUser) applicationUserRepository.findByUsername(username);
        List<ApplicationUser> allUsers = applicationUserRepository.findAll();
        m.addAttribute("allUsers", allUsers);
        m.addAttribute("username", currentUser.getUsername());
        return ("site-users.html");
    }

    @GetMapping("/feed")
    public String getMyFeed(Principal p, Model m) {
        if (p != null) {
            String username = p.getName();
            ApplicationUser currentUser = (ApplicationUser) applicationUserRepository.findByUsername(username);
            m.addAttribute("applicationUser", currentUser);
            List<Post> postsList = new ArrayList<>();
            for (ApplicationUser user : currentUser.getUsersFanOf()) {
                postsList.addAll(user.getPostListByUser());
            }
            m.addAttribute("allUserPosts", postsList);
            m.addAttribute("username", currentUser.getUsername());
        }
        return ("feed.html");
    }

    @PostMapping("/fanFollowsUser")
    public RedirectView followUser(Long userId, Principal p) {
        ApplicationUser fanOfUser = applicationUserRepository.getById(userId);
        String username = p.getName();
        ApplicationUser currentUser = (ApplicationUser) applicationUserRepository.findByUsername(username);
        fanOfUser.addUsersFanOfMe(currentUser);
        currentUser.addUsersFanOf(fanOfUser);
        applicationUserRepository.save(currentUser);
        applicationUserRepository.save(fanOfUser);
        return new RedirectView("/siteusers");
    }
}
