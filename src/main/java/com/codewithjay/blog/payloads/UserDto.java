package com.codewithjay.blog.payloads;

import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserDto {

    private int id;

   @NotEmpty
   @Size(min = 4,message = "UserName must be min of 4 char")
    private String name;

   @Email(message = "Email address is not valid ")
    private String email;

    @NotNull
    @Size(min=3,max = 10, message = "Password must be min of 3 to  max 10 ")
    //@Pattern(regexp = )
    private String password;

    @NotEmpty
    private String about;

}
