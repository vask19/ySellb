package com.vasylkorol.ysellb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {
    private int id;
    private int senderId;
    private int recipientId;
    private List<MessageDto> messageDtoList;

}
