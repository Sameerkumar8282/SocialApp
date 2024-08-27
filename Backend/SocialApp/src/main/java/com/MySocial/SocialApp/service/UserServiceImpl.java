package com.MySocial.SocialApp.service;

import com.MySocial.SocialApp.models.User;
import com.MySocial.SocialApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public User registerUser(User user) {

        User newUser = new User();

        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());

        User savedUser = userRepo.save(newUser);

        return savedUser;
    }

    @Override
    public User findUserById(Integer userId) throws Exception {
        //return Optional means user will be there or not available
        Optional<User> user =  userRepo.findAllById(userId);

        if(user.isPresent()){
            return user.get();
        }
        throw new Exception("User Not exist with userId " + userId);
    }

    @Override
    public User findUserByEmail(String email) {
       User user = userRepo.findByEmail(email);
       return user;
    }

    @Override
    public User followUser(Integer userId1, Integer userId2) throws Exception {
        //follower want to follow user 2 following
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);
        //user 1 want ot follower user 2
        user2.getFollowers().add(user1.getId());
        user1.getFollowings().add(user2.getId());

        userRepo.save(user1);
        userRepo.save(user2);

        return  user1;

    }

    @Override
    public User updateUser(Integer userId,User user) throws Exception {
        Optional<User> foundUser = userRepo.findAllById(userId);
        if(foundUser.isEmpty()){
            throw new Exception("User not exist with that id " + userId);
        }

        User oldUser = foundUser.get();

        if(user.getFirstName() != null){
            oldUser.setFirstName(user.getFirstName());
        }
        if(user.getEmail() != null){
            oldUser.setEmail(user.getEmail());
        }
        if(user.getLastName() != null){
            oldUser.setLastName(user.getLastName());
        }

        User updatedUser = userRepo.save(oldUser);

        return updatedUser;
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepo.searchUser(query);
    }
}
