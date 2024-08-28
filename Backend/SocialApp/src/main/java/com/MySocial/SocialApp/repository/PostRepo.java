package com.MySocial.SocialApp.repository;

import com.MySocial.SocialApp.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//with the help this me manipulate add access the data
public interface PostRepo extends JpaRepository<Post,Integer> {

    @Query("SELECT p from Post p where p.user.id=:userId")
    List<Post> findPostByUserId (Integer userId);
}
