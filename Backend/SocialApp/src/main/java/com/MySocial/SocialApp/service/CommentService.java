package com.MySocial.SocialApp.service;

import com.MySocial.SocialApp.models.Comment;

public interface CommentService {
    public Comment createComment(Comment comment,Integer postId,Integer userID) throws Exception;

    public Comment likedComment(Integer commentId,Integer userId) throws Exception;

    public Comment findCommentById(Integer commentId) throws Exception;
}
