package com.joshuamccluskey.codefellowship.repository;

import com.joshuamccluskey.codefellowship.model.ApplicationUser;
import com.joshuamccluskey.codefellowship.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
