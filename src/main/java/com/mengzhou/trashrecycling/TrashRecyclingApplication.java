package com.mengzhou.trashrecycling;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mengzhou.trashrecycling.mapper")
public class TrashRecyclingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrashRecyclingApplication.class, args);
    }

}
