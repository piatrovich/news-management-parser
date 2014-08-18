package com.epam.lab.news.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:parser.properties")
public class UrlBuilder {
    @Value("${url.prefix}")
    private String prefix;

    public String constructUrl(String id){
        return new StringBuilder()
                .append(prefix)
                .append(id)
                .toString();
    }

}
