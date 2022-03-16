package com.joshuamccluskey.codefellowship.model;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String body;
    String createdAt;

    @ManyToOne
    ApplicationUser postByUser;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public ApplicationUser getPostByUser() {
        return postByUser;
    }

    public void setPostByUser(ApplicationUser postByUser) {
        this.postByUser = postByUser;
    }

    public Post(){

    }

    public Post(String body, String createdAt) {
        this.body = body;
        this.createdAt = createdAt;
    }




}


