package com.vasylkorol.ysellb.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserDto  {

    @NotEmpty(message = "The name cannot be empty")
    @Size(min = 2,max = 100,message = "The name must be in the range from 2 to 100 characters")
    private String username;

    @NotEmpty(message = "The password cannot be empty")
    @Size(min = 6,max = 30,message = "The password must be in the range from 6 to 30 characters")
    private String password;


}
