package com.hhr.test.application;

import com.hhr.test.controller.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@ServletComponentScan(value = "com.hhr.test")
@SpringBootApplication
public class Application
{
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args)
    {
        logger.info("start application...");
        SpringApplication.run(Application.class, args);
        logger.info("end application...");
    }

    @Bean
    public Login getLogin()
    {
        return new Login();
    }
}
