package com.codewithjay.blog.payloads;

import com.codewithjay.blog.entities.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class PostDto {

    private Integer postId;

    private String title ;

    private String content;

    private String imageName;

    private Date addedDate;

    private CategoryDto category;

    private UserDto user;

    private Set<Comment> comments = new HashSet<>();


}
