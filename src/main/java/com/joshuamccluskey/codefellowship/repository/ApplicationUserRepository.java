package com.joshuamccluskey.codefellowship.repository;

import com.joshuamccluskey.codefellowship.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    public UserDetails findByUsername(String username);
    public ApplicationUser findById(long id);
}
