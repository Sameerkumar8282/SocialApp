package com.MySocial.SocialApp.controller;

import com.MySocial.SocialApp.models.Comment;
import com.MySocial.SocialApp.models.User;
import com.MySocial.SocialApp.service.CommentService;
import com.MySocial.SocialApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/comments/post/{postId}")
    public Comment createComment (@RequestBody Comment comment, @RequestHeader("Authorization") String jwt,@PathVariable Integer postId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        return commentService.createComment(comment,postId,user.getId());

    }

    @PutMapping("/comments/like/{commentId}")
    public Comment createComment ( @RequestHeader("Authorization") String jwt,@PathVariable Integer commentId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        return commentService.likedComment(commentId,user.getId());

    }


}
