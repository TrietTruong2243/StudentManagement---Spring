package com.example.QLHS;

import com.example.service.StudentService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.repository")
@ComponentScan({"com.example.service", "com.example.controller" })
@EntityScan({"com.example.model"})
public class QlhsApplication {
    
   
    public static void main(String[] args) {
        SpringApplication.run(QlhsApplication.class, args);

        
    }

}
