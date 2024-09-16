package com.springProject.FirstProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springProject.FirstProject.Entity.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer> {


    
} 
