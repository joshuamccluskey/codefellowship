package com.joshuamccluskey.codefellowship.repository;

import com.joshuamccluskey.codefellowship.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);
}
