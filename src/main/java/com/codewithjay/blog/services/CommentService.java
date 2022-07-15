package com.codewithjay.blog.services;

import com.codewithjay.blog.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Integer postId);

    void deleteComment (Integer commentId);
}
