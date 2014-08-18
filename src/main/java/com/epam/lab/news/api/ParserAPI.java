package com.epam.lab.news.api;

import com.epam.lab.news.bean.Comment;
import com.epam.lab.news.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;

@RestController
@RequestMapping("/comments")
@Consumes("application/json")
public class ParserAPI {
    @Autowired
    CommentService service;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Comment> getComments(@RequestParam(value = "newsId") String id){
        return service.parseComments(id);
    }

}
