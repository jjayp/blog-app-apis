package com.codewithjay.blog.repositories;

import com.codewithjay.blog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
                                      // ctrl + o
public interface CategoryRepo extends JpaRepository<Category, Integer> {



}
