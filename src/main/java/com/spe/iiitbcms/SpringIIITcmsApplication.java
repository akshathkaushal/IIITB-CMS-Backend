package com.spe.iiitbcms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringIIITcmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(com.spe.iiitbcms.SpringIIITcmsApplication.class, args);
    }
}