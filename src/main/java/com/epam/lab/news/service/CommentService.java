package com.epam.lab.news.service;

import com.epam.lab.news.bean.Comment;

public interface CommentService {

    public Iterable<Comment> parseComments(String id);

}
