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
    ApplicationUser applicationUser;

    public Post(){

    }


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

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser postByUser) {
        this.applicationUser = postByUser;
    }



    public Post(String body, String createdAt) {
        this.body = body;
        this.createdAt = createdAt;
    }


}


