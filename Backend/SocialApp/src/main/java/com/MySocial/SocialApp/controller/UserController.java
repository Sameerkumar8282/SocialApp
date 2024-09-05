package com.MySocial.SocialApp.controller;

import com.MySocial.SocialApp.models.User;
import com.MySocial.SocialApp.repository.UserRepo;
import com.MySocial.SocialApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public User createUser(@RequestBody User user){

        User savedUser = userService.registerUser(user);

        return savedUser;
    };

    @GetMapping("/users")
    public List<User> getUsers(){

        List<User> users = userRepo.findAll();
        return users;
    }

    @GetMapping("/user/{id}")
    public User getUsersById(@PathVariable("id") Integer id) throws Exception {
       User findUser = userService.findUserById(id);
       return findUser;
    }

    @PutMapping("/user")
    public User updateUserById( @RequestBody User user,@RequestHeader("Authorization") String jwt) throws Exception {
       User reqUser = userService.findUserByJwt(jwt);
       Integer id = reqUser.getId();
       User updatedUser = userService.updateUser(id,user);
       return updatedUser;
    }

    @PutMapping("/users/follow/{userId2}")
    public User followUserHandler( @PathVariable Integer userId2,@RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        User user = userService.followUser(reqUser.getId(),userId2);
        return user;
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

    @GetMapping("/users/search")
    public List<User> searchUser(@RequestParam("query") String query){
        List<User> users = userService.searchUser(query);
        return users;
    }

    @GetMapping("/user/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt){

        User user = userService.findUserByJwt(jwt);
        return user;
    }

}
