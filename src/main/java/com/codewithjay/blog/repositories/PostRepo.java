package com.codewithjay.blog.repositories;

import com.codewithjay.blog.entities.Category;
import com.codewithjay.blog.entities.Post;
import com.codewithjay.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {


    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    // for searching method

    //List<Post> findByTitleContaining(String title); in this have  hibernate version problem

    @Query("select p from Post p where p.title like :key")
    List<Post> findByTitle(@Param("key") String title);
}

