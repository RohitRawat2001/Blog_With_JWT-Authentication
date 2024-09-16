package com.springProject.FirstProject.service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springProject.FirstProject.Entity.Comment;
import com.springProject.FirstProject.Entity.Post;
import com.springProject.FirstProject.exceptions.ResourceNotFoundException;
import com.springProject.FirstProject.payloads.CommentDto;
import com.springProject.FirstProject.repository.CommentRepo;
import com.springProject.FirstProject.repository.PostRepo;
import com.springProject.FirstProject.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;
    
    @Autowired
    private PostRepo postRepo;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
                  Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "postId", postId));
                  Comment comment = modelMapper.map(commentDto, Comment.class);
                  comment.setPost(post);
                  Comment save = commentRepo.save(comment);
                  return modelMapper.map(save, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
       Comment comment= commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "CommentId", commentId));
       commentRepo.delete(comment);
    }

}
