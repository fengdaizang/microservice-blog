package com.fdzang.microservice.blog.web.listener;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Component
public class OnlineUserListener implements HttpSessionListener {
 
    public static int online = 1;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        online ++;
    }
 
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        online --;
    }
}