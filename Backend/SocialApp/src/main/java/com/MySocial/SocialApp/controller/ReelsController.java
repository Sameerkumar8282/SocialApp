package com.MySocial.SocialApp.controller;

import com.MySocial.SocialApp.models.Reels;
import com.MySocial.SocialApp.models.User;
import com.MySocial.SocialApp.service.ReelsServiceImpl;
import com.MySocial.SocialApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReelsController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReelsServiceImpl reelsService;

    @PostMapping("/reel")
    public Reels createReel(@RequestBody Reels reels, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);

        return reelsService.createReel(reels,user);
    }

    @GetMapping("/reels")
    public List<Reels> findAllReels(){
        return reelsService.findAllReels();
    }

    @GetMapping("/reels/user/{userId}")
    public List<Reels> findUserReels(@PathVariable Integer userId) throws Exception {

        return reelsService.findUserReels(userId);
    }


}
