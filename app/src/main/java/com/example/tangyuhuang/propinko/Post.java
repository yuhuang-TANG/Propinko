package com.example.tangyuhuang.propinko;

/**
 * Created by guillaumehoudayer on 16/12/15.
 */
public class Post {
    private String url, action, params;


    Post(String url, String action, String params){
        this.url = url;
        this.action = action;
        this.params = params;
    }

    public String getUrl(){
        return url;
    }

    public String getAction(){
        return action;
    }

    public String getParams(){
        return params;
    }

}
