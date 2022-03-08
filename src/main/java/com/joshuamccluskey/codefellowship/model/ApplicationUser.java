package com.joshuamccluskey.codefellowship.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Collection;

@Entity
public class ApplicationUser implements UserDetails { //Generate implement methods
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    public  String username;
    public  String password;
    public static String firstName;
    public static String lastName;
    public static LocalDate dateOfBirth;
    public static String bio;

    public ApplicationUser() {

    }

    public ApplicationUser(String username, String password, String firstName, String lastName, LocalDate dateOfBirth, String bio) {
        this.username = username;
        this.password = password;
        this.firstName= firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }


    public static void setUsername(String username) {
        this.username = username;
    }

    public static void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public static void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public static void setBio(String bio) {
        this.bio = bio;
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