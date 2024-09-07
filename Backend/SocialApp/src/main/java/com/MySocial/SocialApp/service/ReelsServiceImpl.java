package com.MySocial.SocialApp.service;

import com.MySocial.SocialApp.models.Reels;
import com.MySocial.SocialApp.models.User;
import com.MySocial.SocialApp.repository.ReelsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelsServiceImpl implements ReelsService{

    @Autowired
    private ReelsRepo reelsRepo;

    @Autowired
    private UserService userService;

    @Override
    public Reels createReel(Reels reels, User user) {
        Reels reel = new Reels();
        reel.setTitle(reels.getTitle());
        reel.setVideo(reels.getVideo());
        reel.setUser(user);

        return reelsRepo.save(reel);
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepo.findAll();
    }

    @Override
    public List<Reels> findUserReels(Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        return  reelsRepo.findByUserId(user.getId());
    }
}
