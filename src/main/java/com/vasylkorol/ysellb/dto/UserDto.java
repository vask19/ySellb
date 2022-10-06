package com.vasylkorol.ysellb.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    private String phoneNumber;
    private boolean active;
    @Builder.Default
    private List<ProductDto> productDtoList = new ArrayList<>();

}
