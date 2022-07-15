package com.codewithjay.blog.services;

import com.codewithjay.blog.payloads.PostDto;
import com.codewithjay.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {

    // create
    PostDto createPost(PostDto postDto,Integer userid,Integer categoryId);

    //Update
    PostDto updatePost(PostDto postDto, Integer postId);

    //delete
    void deletePost(Integer postId);

    //get all post
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy, String sortDir);

    //get Single Post
    PostDto getPostById(Integer postId);

    //get All Post By Category
    List<PostDto> getPostBycategory(Integer categoryId);

    //get Post By user
    List<PostDto> getPostByUser(Integer userId);

    // search Post
    List<PostDto> searchPost(String keyword);

}
