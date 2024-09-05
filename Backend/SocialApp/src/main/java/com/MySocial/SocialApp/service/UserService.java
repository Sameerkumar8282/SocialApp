package com.MySocial.SocialApp.service;

import com.MySocial.SocialApp.models.User;

import java.util.List;

public interface UserService {

    public User registerUser(User user);

    public User findUserById(Integer userId) throws Exception;

    public User findUserByEmail(String email);

    public User followUser(Integer follower, Integer following) throws Exception;

    public User updateUser(Integer id ,User user) throws Exception;

    public List<User>  searchUser(String query);

    public User findUserByJwt(String jwt);
}
