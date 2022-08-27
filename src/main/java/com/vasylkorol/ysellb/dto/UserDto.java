package com.vasylkorol.ysellb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto  {

    private int id;

    private String firstName;
    private String secondName;
    private String email;
    private String username;
    private String phoneNumber;
    private boolean isActive;





}
