package com.codewithjay.blog.services;

import com.codewithjay.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

   // category
      CategoryDto createCategory(CategoryDto categoryDto);

    // updadate
      CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    //delete
     void deleteCategory(Integer categoryId);

    //get
    CategoryDto getCategory(Integer categoryId);

    //getAll

    List<CategoryDto> getCategory();

}
