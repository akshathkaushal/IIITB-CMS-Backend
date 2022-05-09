package com.spe.iiitbcms;

import org.testcontainers.containers.MySQLContainer;

public abstract class BaseTest {

    static MySQLContainer mySQLContainer = (MySQLContainer) new MySQLContainer("mysql:latest")
            .withDatabaseName("spring-spe-iiitbcms")
            .withUsername("rollNo")
            .withPassword("password")
            .withReuse(true);

    static {
        mySQLContainer.start();
    }
}