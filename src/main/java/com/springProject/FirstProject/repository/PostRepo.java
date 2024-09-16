package com.springProject.FirstProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springProject.FirstProject.Entity.Category;
import com.springProject.FirstProject.Entity.Post;
import com.springProject.FirstProject.Entity.User;

import java.util.*;

public interface PostRepo extends JpaRepository<Post,Integer> {

    //custom methods
    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    //searching the keyword
    List<Post> findByTitleContaining(String title);

}
