package com.vasylkorol.ysellb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class YSellbApplication {

    public static void main(String[] args) {
        SpringApplication.run(YSellbApplication.class, args);
    }

}
