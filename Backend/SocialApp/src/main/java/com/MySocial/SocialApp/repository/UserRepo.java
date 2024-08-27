package com.MySocial.SocialApp.repository;

import com.MySocial.SocialApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findAllById(Integer id);
}
