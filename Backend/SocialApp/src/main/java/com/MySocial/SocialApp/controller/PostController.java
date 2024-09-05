package com.MySocial.SocialApp.controller;

import com.MySocial.SocialApp.models.Post;
import com.MySocial.SocialApp.models.User;
import com.MySocial.SocialApp.response.ApiResponse;
import com.MySocial.SocialApp.service.PostService;
import com.MySocial.SocialApp.service.UserService;
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

    @Autowired
    UserService userService;

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post createdPost = postService.createNewPost(post,reqUser.getId());
        return new ResponseEntity<>(createdPost, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId,@RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        String message =  postService.deletePost(postId,reqUser.getId());
        ApiResponse res = new ApiResponse(message,true);

        return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {
        Post post = postService.findPostById(postId);
        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }

    @GetMapping("/posts/user")
    public ResponseEntity<List<Post>> findUserPost(@RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        List<Post> posts= postService.findPostByUserId(reqUser.getId());

        return  new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findUserPost()  {
        List<Post> posts = postService.findAllPost();
        return  new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
    }

    @PutMapping("/posts/save/{postId}f")
    public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId,@RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post posts = postService.savedPost(postId,reqUser.getId());
        return  new ResponseEntity<Post>(posts,HttpStatus.OK);
    }

    @PutMapping("/posts/like/{postId}")
    public ResponseEntity<Post> postLiked (@PathVariable Integer postId,@RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post posts = postService.likedPost(postId,reqUser.getId());
        return  new ResponseEntity<Post>(posts,HttpStatus.OK);
    }
}
