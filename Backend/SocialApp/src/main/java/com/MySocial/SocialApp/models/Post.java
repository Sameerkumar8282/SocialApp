package com.MySocial.SocialApp.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@Entity map the db table to this class
@Entity
@Table(name = "post_db")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String caption;

    private String image;

    private String video;

    private User user;

    private List<User> liked = new ArrayList<>();

    private LocalDate createdAt;

    public Post(){

    }

    public Post(Integer id, String caption, String image, String video, User user, List<User> liked, LocalDate createdAt) {
        this.id = id;
        this.caption = caption;
        this.image = image;
        this.video = video;
        this.user = user;
        this.liked = liked;
        this.createdAt = createdAt;
    }

    public List<User> getLiked() {
        return liked;
    }

    public void setLiked(List<User> liked) {
        this.liked = liked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}

