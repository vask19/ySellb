package com.vasylkorol.ysellb.dto;

import com.vasylkorol.ysellb.model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto  {
    private int id;
    private String firstName;
    private String secondName;
    private String email;
    private String username;
    private String password;
    private String phoneNumber;
    private boolean isActive;





}
