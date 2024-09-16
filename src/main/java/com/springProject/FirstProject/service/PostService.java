package com.springProject.FirstProject.service;

import com.springProject.FirstProject.payloads.PostDto;
import com.springProject.FirstProject.payloads.PostResponse;

import java.util.*;

public interface PostService {

    //create
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    //update
    PostDto updatePost(PostDto postDto,Integer postId);

    //delete
    void deletePost(Integer postId);

    //get single post
    PostDto getSinglePost(Integer postId);

    //get All posts
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    //get all posts by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    //get all posts by user
    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> searchPosts(String keyword);
    
}