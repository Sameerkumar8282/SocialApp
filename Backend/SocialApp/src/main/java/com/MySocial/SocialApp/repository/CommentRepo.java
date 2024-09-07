package com.MySocial.SocialApp.repository;

import com.MySocial.SocialApp.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
