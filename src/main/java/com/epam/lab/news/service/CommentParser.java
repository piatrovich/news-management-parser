package com.epam.lab.news.service;

import com.epam.lab.news.bean.Comment;
import com.epam.lab.news.util.UrlBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Component
public class CommentParser implements CommentService {
    @Autowired
    UrlBuilder urlBuilder;

    @Value("${user.agent.header}")
    private String agentHeader;

    @Value("${user.agent.name}")
    private String agentValue;

    private String html;

    @Override
    public Iterable<Comment> parseComments(String id) {
        List<Comment> comments = new ArrayList<Comment>();
        try {
            Document document = Jsoup.connect(urlBuilder.constructUrl(id))
                    .userAgent(agentValue)
                    .timeout(10000)
                    .get();
            Elements parsedComments = document.select("li.d2-comment.d2-comment-no-replies");
            for(Element element : parsedComments) {
                Comment comment = new Comment();
                comment.setAuthor(element.select("a.d2-username").first().text());
                comment.setMessage(element.select("div.d2-body").first().text());
                DateFormat format = new SimpleDateFormat("dd MMMM yyyy hh:mma", Locale.US);
                Date date = format.parse(element.select("p.d2-datetime > a").first().text());
                comment.setTimestamp(date.getTime());
                comments.add(comment);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e){
            e.printStackTrace();
        }
        return comments;
    }



    private void loadHtmlContent(String link) {
        try {
            URL url = new URL(link);
            URLConnection connection = url.openConnection();
            connection.addRequestProperty(agentHeader, agentValue);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String inputLine;
            while ((inputLine = reader.readLine()) != null)
                builder.append(inputLine);
            reader.close();
            html = builder.toString();
        } catch (IOException e) {
            html = "";
        }
    }

}
