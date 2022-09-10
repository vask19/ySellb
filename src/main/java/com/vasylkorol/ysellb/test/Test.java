package com.vasylkorol.ysellb.test;

import com.vasylkorol.ysellb.payload.request.EmailReceiver;

import java.util.Random;
import java.util.UUID;

public class Test {
    public static void main(String[] args) {
        sendAuthCode(new EmailReceiver());
    }
    public static void sendAuthCode(EmailReceiver emailReceiver){
        int code = (new Random().nextInt(100000,900000));

    }
}
