package com.codewithjay.blog.controllers;

import com.codewithjay.blog.payloads.ApiResponse;
import com.codewithjay.blog.payloads.UserDto;
import com.codewithjay.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class
UserController {

    @Autowired
    private UserService userService;

    // POST-create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){

      UserDto createUserDto =  this.userService.createUser(userDto);
      return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    // PUT-Update user
     @PutMapping("/{userId}")
     public ResponseEntity<UserDto>updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
      UserDto updatedUser =  this.userService.updateUser(userDto,userId);
      return ResponseEntity.ok(updatedUser);
     }

    // DELETE= Delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer uid){
        this.userService.deleteUser(uid );
       // return new ResponseEntity.(Map.of("message","User Deleted Successfully "), HttpStatus.OK);
        return new ResponseEntity(new ApiResponse("User Deleted Successfully ",true), HttpStatus.OK);
    }

    // GET- user get
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));

    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.ok(this.userService.getAllUsers());

    }


}
