package com.codewithjay.blog.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Data
public class CategoryDto {

    private Integer categoryId;

    @NotBlank
    @Size(min = 5, max = 50)
    private  String categoryTitle;

    @Size(min = 10,max = 500)
    private String categoryDescription;

}
