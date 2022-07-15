package com.codewithjay.blog.controllers;

import com.codewithjay.blog.payloads.ApiResponse;
import com.codewithjay.blog.payloads.CategoryDto;
import com.codewithjay.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //create
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createCategory =this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);


    }

    //update
    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory( @Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId){
        CategoryDto updateCategory= this.categoryService.updateCategory(categoryDto, catId);
        return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);

    }
    //delete
    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deletedCategory(@PathVariable Integer catId){
       this.categoryService.deleteCategory(catId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully",
                true), HttpStatus.OK);

    }

    //get

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable Integer catId){
      CategoryDto categoryDto =  this.categoryService.getCategory(catId);
        return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);

    }

    //getall

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getallCategory(){
        List<CategoryDto> category =  this.categoryService.getCategory();
        return ResponseEntity.ok(category);
    }

}
