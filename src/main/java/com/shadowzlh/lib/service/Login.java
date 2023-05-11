package com.shadowzlh.lib.service;

import com.shadowzlh.lib.common.Constants;
import com.shadowzlh.lib.common.Request;
import com.shadowzlh.lib.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class Login {
    public static String login(User user){

        HttpPost httpPost = Request.httpPost(Constants.getLoginUrl());
        try{
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("username",user.getUsername()));
        nvps.add(new BasicNameValuePair("password",user.getPassword()));
        nvps.add(new BasicNameValuePair("verify", Captcha.getNormativeCaptcha(user)));

        httpPost.setHeader("Referer",Constants.getLoginReferer());
        HttpEntity httpEntity = null;
        HttpResponse httpResponse = null;
        String responseJson = null;

            httpEntity = new UrlEncodedFormEntity(nvps,"UTF-8");
            httpPost.setEntity(httpEntity);
            httpResponse = user.getHttpClient().execute(httpPost);
            HttpEntity responseEntity = httpResponse.getEntity();
            responseJson = EntityUtils.toString(responseEntity);
            responseJson = StringEscapeUtils.unescapeJava(responseJson);
            if (responseJson.contains("验证码错误")) return "";
            return responseJson;
        }catch (Exception e){
            log.error(e.toString());
            return "";
        }
    }

    public static String SuccessLogin(User user){
        String login = "";
        while (!(login = login(user)).contains("登陆成功")) {
            if (login.contains("登录密码不正确")) return "登录密码不正确";
            if (login.contains("对不起，您汇文系统用户名和密码错误，请联系图书馆。")) return "用户名不正确";
        }

        log.info(login);
        return "Success";
    }

}
