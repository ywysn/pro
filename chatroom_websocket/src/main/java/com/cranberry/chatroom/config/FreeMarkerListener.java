package com.cranberry.chatroom.config;

import	java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import freemarker.template.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class FreeMarkerListener implements ServletContextListener {
    public static final String TEMPLATE_KEY = "_template_";
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
        try {
            cfg.setDirectoryForTemplateLoading(new File("D:\\JavaCode\\chatroom_websocket\\src\\main\\java\\com\\webapp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        cfg.setDefaultEncoding(StandardCharsets.UTF_8.displayName());
        sce.getServletContext().setAttribute(TEMPLATE_KEY,cfg);
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}



