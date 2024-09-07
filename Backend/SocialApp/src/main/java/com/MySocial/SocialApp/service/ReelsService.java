package com.MySocial.SocialApp.service;

import com.MySocial.SocialApp.models.Reels;
import com.MySocial.SocialApp.models.User;

import java.util.List;

public interface ReelsService {
    public Reels createReel(Reels reels, User user);

    public List<Reels> findAllReels();

    public List<Reels> findUserReels(Integer userId) throws Exception;
}
