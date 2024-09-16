package com.springProject.FirstProject.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springProject.FirstProject.config.ApiConstant;
import com.springProject.FirstProject.payloads.ApiResponse;
import com.springProject.FirstProject.payloads.PostDto;
import com.springProject.FirstProject.payloads.PostResponse;
import com.springProject.FirstProject.service.FileService;
import com.springProject.FirstProject.service.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
            @PathVariable Integer categoryId) {
        PostDto postDto2 = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(postDto2, HttpStatus.CREATED);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto postDto2 = postService.updatePost(postDto, postId);
        return new ResponseEntity<>(postDto2, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post is deleted Successfully !!", true), HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getSinglePost(@PathVariable Integer postId) {
        PostDto postDto = postService.getSinglePost(postId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = ApiConstant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = ApiConstant.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = ApiConstant.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = ApiConstant.SORT_DIR, required = false) String sortDir) {
        PostResponse allPosts = postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryID}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryID) {
        List<PostDto> postsByCategory = postService.getPostsByCategory(categoryID);
        return new ResponseEntity<>(postsByCategory, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
        List<PostDto> postsByUser = postService.getPostsByUser(userId);
        return new ResponseEntity<>(postsByUser, HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPosts(@PathVariable String keyword) {
        List<PostDto> searchPosts = postService.searchPosts(keyword);
        return new ResponseEntity<>(searchPosts, HttpStatus.OK);
    }

    // upload image

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadImagePost(
            @RequestParam("image") MultipartFile image,
            @PathVariable Integer postId) throws IOException {

        PostDto singlePost = postService.getSinglePost(postId);
        String fileName = fileService.uploadImage(path, image);
        singlePost.setImageName(fileName);
        PostDto updatePost = postService.updatePost(singlePost, postId);
        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }

    
    @GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
            throws IOException {
        InputStream resource = fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}
