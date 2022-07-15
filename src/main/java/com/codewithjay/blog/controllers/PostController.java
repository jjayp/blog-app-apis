package com.codewithjay.blog.controllers;

import com.codewithjay.blog.config.AppConstants;
import com.codewithjay.blog.payloads.ApiResponse;
import com.codewithjay.blog.payloads.PostDto;
import com.codewithjay.blog.payloads.PostResponse;
import com.codewithjay.blog.services.FileService;
import com.codewithjay.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createpost(@RequestBody PostDto postDto,@PathVariable Integer userId,
                                              @PathVariable Integer categoryId){
       PostDto createPost = this.postService.createPost(postDto,userId,categoryId);

        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }
//Post get by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
        List<PostDto> posts = this.postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

    }
    //Post get by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostBycategory(@PathVariable Integer categoryId){
        List<PostDto> posts = this.postService.getPostBycategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

    }

    // get all post

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost
            (@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber
            ,@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer PageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
             @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir){

       PostResponse postResponse = this.postService.getAllPost(pageNumber, PageSize, sortBy, sortDir);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    //get post by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getSinglePost(@PathVariable Integer postId){
      PostDto postDto = this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);

    }

    @DeleteMapping("/{postId}")
    public ApiResponse detelePost(@PathVariable Integer postId){
       this.postService.deletePost(postId);
       return new ApiResponse("Post us successfully deleted",true);
    }
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
        PostDto updatePost = this.postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
    }

    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords")String keywords){
        List<PostDto> result=this.postService.searchPost(keywords);
        return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
    }
    @PostMapping("/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image")MultipartFile image,
           @PathVariable Integer postId
        ) throws IOException {

        PostDto postDto = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path, image);

        postDto.setImageName(fileName);
       PostDto updatePost = this.postService.updatePost(postDto,postId);
       return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);

    }
    @GetMapping(value = "/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {

        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

}
