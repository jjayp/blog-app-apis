package com.codewithjay.blog.services.impl;

import com.codewithjay.blog.entities.Comment;
import com.codewithjay.blog.entities.Post;
import com.codewithjay.blog.exception.ResourceNotFoundException;
import com.codewithjay.blog.payloads.CommentDto;
import com.codewithjay.blog.repositories.CommentRepo;
import com.codewithjay.blog.repositories.PostRepo;
import com.codewithjay.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post = this.postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post","Post id",postId));
         Comment comment =this.modelMapper.map(commentDto, Comment.class);
         comment.setPost(post);
         Comment savedComment = this.commentRepo.save(comment);
         return this.modelMapper.map(savedComment,CommentDto.class);

    }

    @Override
    public void deleteComment(Integer commentId) {

     Comment comment = commentRepo.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException("Comment","commentId",commentId));
     this.commentRepo.delete(comment);



    }
}
