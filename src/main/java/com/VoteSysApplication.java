package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.cq.edu.dao")
public class VoteSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoteSysApplication.class, args);
    }

}
