package com.shadowzlh.lib.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.context.annotation.Bean;

@Slf4j
public class Request {
    public static RequestConfig requestConfig;
    public static HttpClient httpClient;

    static{

        httpClient = new DefaultHttpClient();
        requestConfig = RequestConfig.custom().setConnectionRequestTimeout(3000)
                .setConnectTimeout(3000)
                .setSocketTimeout(3000)
                .build();
    }

    public static HttpGet httpGet(String url){
        /*创建httpGet远程连接实例*/
        HttpGet httpGet = new HttpGet(url);

        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(6000)
                .setConnectTimeout(6000)
                .setSocketTimeout(6000)
                .build();

        httpGet.setConfig(requestConfig);
        httpGet.setHeader("Referer",Constants.getLoginReferer());
        httpGet.setHeader("Content-Type"," application/x-www-form-urlencoded; charset=UTF-8");



        return httpGet;
    }

    public static HttpPost httpPost (String url){
        /*创建httpGet远程连接实例*/
        HttpPost httpPost = new HttpPost(url);

        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(6000)
                .setConnectTimeout(6000)
                .setSocketTimeout(6000)
                .build();

        httpPost.setConfig(requestConfig);

        httpPost.setHeader("Content-Type"," application/x-www-form-urlencoded; charset=UTF-8");
        httpPost.setHeader("Referer",Constants.getLoginReferer());

        return httpPost;
    }
}
