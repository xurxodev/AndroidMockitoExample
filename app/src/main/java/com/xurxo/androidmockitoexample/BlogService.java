package com.xurxo.androidmockitoexample;

/**
 * Created by xurxo on 16/11/2014.
 */
public class BlogService {
    Logger logger;
    HtmlValidator htmlValidator;

    public BlogService(Logger logger, HtmlValidator htmlValidator){
        this.logger = logger;
        this.htmlValidator = htmlValidator;
    }

    private void Log(String message){
        if (logger != null)
            logger.log(message);
    }

    public boolean publishPost(String html){
        if (htmlValidator.isValid(html))
        {
            Log("Post has published");

            return true;
        }
        else
        {
            Log("publish error, html not valid");

            throw new IllegalArgumentException("html not valid");
        }
    }
}
