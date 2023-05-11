package com.shadowzlh.lib.service;

import cn.hutool.json.JSONUtil;
import com.shadowzlh.lib.common.Request;
import com.shadowzlh.lib.domain.BookResult.Result;
import com.shadowzlh.lib.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Book {
    public static String book(User user, String url, String segment)  {

        DefaultHttpClient httpClient = user.getHttpClient();
        /*创建httpGet远程连接实例*/


        HttpPost httpPost = Request.httpPost(url);

        List<NameValuePair> nvps = new ArrayList<>();

        nvps.add(new BasicNameValuePair("userid",user.getUsername()));
        nvps.add(new BasicNameValuePair("access_token",user.getAccessToken()));
        nvps.add(new BasicNameValuePair("segment",segment));
        nvps.add(new BasicNameValuePair("type", "1"));


        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            String entityStr = EntityUtils.toString(responseEntity);
            entityStr = StringEscapeUtils.unescapeJava(entityStr);
            Result result = JSONUtil.toBean(entityStr, Result.class);
            if (result == null || StringUtils.isEmpty(result.getMsg()))
                return "Error";
            log.info(entityStr);
            if (result.getMsg().contains("预约成功")) return "Success";
            return "false";
        }catch (Exception e){
            e.printStackTrace();
            return "Error";
        }
    }

//    默认请求三次, 三次因网络或其他原因, 视为不成功
    public static boolean successBook(User user, String url, String segment){
        for (int i = 1; i < 5; i ++){
            String book = book(user, url, segment);
            if (StringUtils.isEmpty(book)) return false;
            if (book.equals("Success")){
                return true;
            }
            else if (book.equals("false"))  return false;
        }
        return false;
    }

}
