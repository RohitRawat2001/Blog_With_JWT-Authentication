package com.springProject.FirstProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springProject.FirstProject.Entity.User;
import java.util.*;


public interface UserRepo extends JpaRepository<User,Integer>{

    Optional<User> findByEmail(String email);
} 