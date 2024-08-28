package com.MySocial.SocialApp.models;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_db")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String gender;

    //null hi empty hona chia
    private List<Integer> followers = new ArrayList<>();

    private List<Integer> followings = new ArrayList<>();

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

    public List<Post> getSavedPost() {
        return savedPost;
    }

    public void setSavedPost(List<Post> savedPost) {
        this.savedPost = savedPost;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Integer> followers) {
        this.followers = followers;
    }

    public List<Integer> getFollowings() {
        return followings;
    }

    public void setFollowings(List<Integer> followings) {
        this.followings = followings;
    }

    public User(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
