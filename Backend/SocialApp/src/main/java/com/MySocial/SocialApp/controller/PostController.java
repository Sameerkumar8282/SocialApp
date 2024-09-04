package com.MySocial.SocialApp.controller;

import com.MySocial.SocialApp.models.Post;
import com.MySocial.SocialApp.response.ApiResponse;
import com.MySocial.SocialApp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/posts/{userId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post,@PathVariable Integer userId) throws Exception {
        Post createdPost = postService.createNewPost(post,userId);
        return new ResponseEntity<>(createdPost, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/{userId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {
       String message =  postService.deletePost(postId,userId);
        ApiResponse res = new ApiResponse(message,true);

        return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {
        Post post = postService.findPostById(postId);
        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }

    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUserPost(@PathVariable Integer userId) throws Exception {
        List<Post> posts= postService.findPostByUserId(userId);

        return  new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findUserPost()  {
        List<Post> posts = postService.findAllPost();
        return  new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
    }

    @PutMapping("/posts/save/{postId}/user/{userId}")
    public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId,@PathVariable Integer userId) throws Exception {
        Post posts = postService.savedPost(postId,userId);
        return  new ResponseEntity<Post>(posts,HttpStatus.OK);
    }

    @PutMapping("/posts/like/{postId}/user/{userId}")
    public ResponseEntity<Post> postLiked (@PathVariable Integer postId,@PathVariable Integer userId) throws Exception {
        Post posts = postService.likedPost(postId,userId);
        return  new ResponseEntity<Post>(posts,HttpStatus.OK);
    }
}
