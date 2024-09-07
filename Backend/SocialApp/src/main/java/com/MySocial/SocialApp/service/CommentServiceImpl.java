package com.MySocial.SocialApp.service;

import com.MySocial.SocialApp.models.Comment;
import com.MySocial.SocialApp.models.Post;
import com.MySocial.SocialApp.models.User;
import com.MySocial.SocialApp.repository.CommentRepo;
import com.MySocial.SocialApp.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;

    @Override
    public Comment createComment(Comment comment, Integer postId, Integer userID) throws Exception {

        User user = userService.findUserById(userID);
        Post post = postService.findPostById(postId);
        comment.setUser(user);
        comment.setContent(comment.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        Comment savedComment = commentRepo.save(comment);

        post.getComments().add(savedComment);

        postRepo.save(post);

        return savedComment;
    }

    @Override
    public Comment likedComment(Integer commentId, Integer userId) throws Exception {
        Comment comment =  findCommentById(commentId);
        User user = userService.findUserById(userId);
        if(!comment.getLiked().contains(user)){
            comment.getLiked().add(user);
        }else{
            comment.getLiked().remove(user);
        }
        return commentRepo.save(comment);
    }

    @Override
    public Comment findCommentById(Integer commentId) throws Exception {
        Optional<Comment> opt = commentRepo.findById(commentId);
        if(opt.isEmpty()){
            throw new Exception("Comment not exist");
        }
        return opt.get();
    }
}
