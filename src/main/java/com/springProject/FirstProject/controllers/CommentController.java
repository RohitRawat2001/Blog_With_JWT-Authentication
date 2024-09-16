package com.springProject.FirstProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springProject.FirstProject.payloads.ApiResponse;
import com.springProject.FirstProject.payloads.CommentDto;
import com.springProject.FirstProject.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @PostMapping("post/{postId}/comments")
    public ResponseEntity<CommentDto>  createComment(@RequestBody CommentDto commentDto,@PathVariable Integer postId) {
        CommentDto createComment = commentService.createComment(commentDto, postId);
        return new ResponseEntity<>(createComment,HttpStatus.CREATED);

    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted SuccessFully !!",true),HttpStatus.OK);
        
    }


}
