package com.vasylkorol.ysellb.dto;

import com.vasylkorol.ysellb.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



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
