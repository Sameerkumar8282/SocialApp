package com.MySocial.SocialApp.service;

import com.MySocial.SocialApp.models.Post;
import com.MySocial.SocialApp.models.User;
import com.MySocial.SocialApp.repository.PostRepo;
import com.MySocial.SocialApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @Override
    public Post createNewPost(Post post, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        Post newPost = new Post();

        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setCreatedAt(LocalDate.now());
        newPost.setVideo(post.getVideo());
        newPost.setUser(user);

        return newPost;
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {
       Post post = findPostById(postId);
       User user = userService.findUserById(userId);

       //check if that userId and user is same in Post model
        if(post.getUser().getId() != user.getId()){
            throw new Exception("You can't delete other users Posts");
        }

        postRepo.deleteById(postId);
        return "Post Deleted Successfully!";
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) throws Exception {
        return postRepo.findPostByUserId(userId);
    }

    public Post findPostById(Integer postId) throws Exception {
        Optional<Post> post = postRepo.findById(postId);
        if(post.isEmpty()){
            throw new Exception("No Post Exist with id: " + postId);
        }

        return post.get();
    }

    @Override
    public List<Post> findAllPost() {
        return postRepo.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if(user.getSavedPost().contains(post)){
            user.getSavedPost().remove(post);
        }else{
            user.getSavedPost().add(post);
        }

        userRepo.save(user);
        return post;
    }

    @Override
    public Post likedPost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if(post.getLiked().contains(user)){
            post.getLiked().remove(user);
        }else {
            post.getLiked().add(user);
        }
        return postRepo.save(post);
    }
}
