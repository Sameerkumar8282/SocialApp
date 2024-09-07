package com.MySocial.SocialApp.repository;

import com.MySocial.SocialApp.models.Reels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReelsRepo  extends JpaRepository<Reels,Integer> {

    public List<Reels> findByUserId(Integer userId);
}
