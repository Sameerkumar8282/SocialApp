package com.MySocial.SocialApp.controller;

import com.MySocial.SocialApp.models.User;
import com.MySocial.SocialApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepo userRepo;

    @PostMapping("/user")
    public User createUser(@RequestBody User user){
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());

        User savedUser = userRepo.save(newUser);

        return savedUser;
    };

    @GetMapping("/users")
    public List<User> getUsers(){

        List<User> users = userRepo.findAll();
        return users;
    }

    @GetMapping("/user/{id}")
    public User getUsersById(@PathVariable("id") Integer id) throws Exception {
        //return Optional means user will be there or not available
        Optional<User> user =  userRepo.findAllById(id);

        if(user.isPresent()){
            return user.get();
        }
        throw new Exception("User Not exist with userId " + id);
    }

    @PutMapping("/user/{id}")
    public User updateUserById(@PathVariable("id") Integer id, @RequestBody User user) throws Exception {
        Optional<User> foundUser = userRepo.findAllById(id);
        if(foundUser.isEmpty()){
            throw new Exception("User not exist with that id " + id);
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

    @DeleteMapping("user/{id}")
    public String deleteUserById(@PathVariable("id") Integer userId) throws Exception{
        Optional<User> user = userRepo.findAllById(userId);
        if(user.isEmpty()){
            throw new Exception("User Doesn't Exist with That Id" + userId);
        }
        userRepo.delete(user.get());

        return "User Deleted Successfully with userId " + userId ;
    }



}
