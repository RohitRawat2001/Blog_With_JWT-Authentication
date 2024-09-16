package com.springProject.FirstProject.service;

import com.springProject.FirstProject.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Integer postId);
    void deleteComment(Integer commentId);
}
