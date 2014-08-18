package com.epam.lab.news.runner;

import com.epam.lab.news.configuration.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;

public class Runner {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfiguration.class);
    }

}
