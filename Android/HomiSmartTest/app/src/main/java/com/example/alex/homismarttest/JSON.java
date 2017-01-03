package com.example.alex.homismarttest;

/**
 * Created by resin on 06/11/2016.
 */

public class JSON {

    private String host;
    private String userAgent;

    public JSON(String host, String userAgent) {
        this.host = host;
        this.userAgent = userAgent;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
