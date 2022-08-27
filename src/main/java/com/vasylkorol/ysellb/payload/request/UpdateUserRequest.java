package com.vasylkorol.ysellb.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {


    private String firstName;
    private String secondName;
    private String email;
    private String phoneNumber;
}
