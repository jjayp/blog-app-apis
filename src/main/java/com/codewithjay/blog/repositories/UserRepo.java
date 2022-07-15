package com.codewithjay.blog.repositories;

import com.codewithjay.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    @Override
    User getById(Integer integer);
}
