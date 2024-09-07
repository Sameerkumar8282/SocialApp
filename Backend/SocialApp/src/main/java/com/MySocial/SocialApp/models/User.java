package com.MySocial.SocialApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "user_db")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    @JsonIgnore
    private String password;

    private String gender;

    //null nhi empty hona chia
    private List<Integer> followers = new ArrayList<>();

    private List<Integer> followings = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    private List<Post> savedPost = new ArrayList<>();

    public User(Integer id, List<Integer> followings, String firstName, String lastName, String email, String password, List<Integer> followers, String gender, List<Post> savedPost) {
        this.id = id;
        this.followings = followings;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.followers = followers;
        this.gender = gender;
        this.savedPost = savedPost;
    }

    public User(){

    }

}
