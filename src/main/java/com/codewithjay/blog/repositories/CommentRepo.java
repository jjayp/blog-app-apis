package com.codewithjay.blog.repositories;

import com.codewithjay.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {


}
