package com.vasylkorol.ysellb.exception;

import lombok.Data;

@Data
public class ChatNotFoundException extends RuntimeException {
    public ChatNotFoundException(){
        super("Chat not found");
    }
}
