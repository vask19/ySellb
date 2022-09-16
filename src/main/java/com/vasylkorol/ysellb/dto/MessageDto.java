package com.vasylkorol.ysellb.dto;

import com.vasylkorol.ysellb.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {
    private int id;
    private String text;
    private LocalDateTime dateOfCreate;
}
