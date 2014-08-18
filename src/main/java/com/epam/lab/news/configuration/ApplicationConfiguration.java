package com.epam.lab.news.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = "com.epam.lab.news")
@EnableAutoConfiguration
@EnableAspectJAutoProxy
public class ApplicationConfiguration {

    /**
     * This configurer needs for loading data from properties.
     *
     * @return PropertySourcesPlaceholderConfigurer object
     */
    @Bean
    public PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
