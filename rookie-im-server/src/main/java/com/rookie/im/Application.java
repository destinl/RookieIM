package com.rookie.im;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @Description: TODO
 * @Author: ls
 * @Date: 2024/6/1718:11
 */
@SpringBootApplication
@MapperScan(basePackages = "com.rookie.im.**.mapper")
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
