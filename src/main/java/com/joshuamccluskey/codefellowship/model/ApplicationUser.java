package com.joshuamccluskey.codefellowship.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

import java.util.List;
import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails { //Generate implement methods
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String username;
    String password;
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    String bio;
    String pic = "defaultProfile.png";



    @OneToMany(mappedBy = "applicationUser", cascade = CascadeType.ALL)
    List<Post> postListByUser;

    @ManyToMany(mappedBy = "usersFanOfMe")
    Set<ApplicationUser> usersFanOf;

    @ManyToMany
    @JoinTable(name = "usersName_to_fan",
            joinColumns = {@JoinColumn(name="usersName")},
            inverseJoinColumns = {@JoinColumn(name="fan")})
    Set<ApplicationUser> usersFanOfMe;



    public ApplicationUser(){
        /* This is the default method */
    }
    public ApplicationUser(String username, String password, String firstName, String lastName, LocalDate dateOfBirth ,String bio) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }

    public void addUsersFanOfMe (ApplicationUser currentUser){
        usersFanOfMe.add(currentUser);
    }
    public void addUsersFanOf (ApplicationUser fanOfUser){
        usersFanOf.add(fanOfUser);
    }
    public Set<ApplicationUser> getUsersFanOf() {
        return usersFanOf;
    }

    public void setUsersFanOf(Set<ApplicationUser> usersFanOf) {
        this.usersFanOf = usersFanOf;
    }

    public Set<ApplicationUser> getUsersFanOfMe() {
        return usersFanOfMe;
    }

    public void setUsersFanOfMe(Set<ApplicationUser> usersFanOfMe) {
        this.usersFanOfMe = usersFanOfMe;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public List<Post> getPostListByUser() {
        return postListByUser;
    }

    public void setPostListByUser(List<Post> postListByUsers) {
        this.postListByUser = postListByUsers;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}