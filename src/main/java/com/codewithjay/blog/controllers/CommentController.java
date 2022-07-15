package com.codewithjay.blog.controllers;

import com.codewithjay.blog.payloads.ApiResponse;
import com.codewithjay.blog.payloads.CommentDto;
import com.codewithjay.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId){
      CommentDto createComment =  this.commentService.createComment(commentDto, postId);
      return new ResponseEntity<>(createComment, HttpStatus.CREATED);

    }

    @DeleteMapping("/comments/{coomentId}")
    public ApiResponse deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ApiResponse("Comment deleted successfully",true);
    }
}
