package com.springProject.FirstProject.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate; 
    private UserDto user;
    private CategoryDto category;
    private Set<CommentDto> comments = new HashSet<>();
    
}
