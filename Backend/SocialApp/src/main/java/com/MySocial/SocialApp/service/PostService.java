package com.MySocial.SocialApp.service;

import com.MySocial.SocialApp.models.Post;

import java.util.List;

public interface PostService {

    //Exception because user should be available in the DB
    Post createNewPost(Post post, Integer userId) throws Exception;

    String deletePost(Integer postId,Integer UserId) throws Exception;

    List<Post> findPostByUserId(Integer userId) throws Exception;

    Post findPostById(Integer postId )throws Exception;

    List<Post> findAllPost();

    Post savedPost(Integer postId,Integer userId) throws Exception;

    Post likedPost(Integer postId,Integer userId) throws Exception;
}
