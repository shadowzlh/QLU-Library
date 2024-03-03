package com.shadowzlh.lib.service;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import com.shadowzlh.lib.common.Constants;
import com.shadowzlh.lib.common.Request;
import com.shadowzlh.lib.domain.User;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.security.MD5Encoder;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class Login {
    public static String login(User user){

        try{
            // 以前的策略，使用 tessdata 进行验证码 ocr 识别
//            HttpPost httpPost = Request.httpPost(Constants.getLoginUrl());
//            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//            nvps.add(new BasicNameValuePair("username",user.getUsername()));
//            nvps.add(new BasicNameValuePair("password",user.getPassword()));
//            nvps.add(new BasicNameValuePair("verify", Captcha.getNormativeCaptcha(user)));
//
//            httpPost.setHeader("Referer",Constants.getLoginReferer());
//            HttpEntity httpEntity = null;
//            HttpResponse httpResponse = null;
//            String responseJson = null;
//
//            httpEntity = new UrlEncodedFormEntity(nvps,"UTF-8");
//            httpPost.setEntity(httpEntity);
//            httpResponse = user.getHttpClient().execute(httpPost);
//            HttpEntity responseEntity = httpResponse.getEntity();
//            responseJson = EntityUtils.toString(responseEntity);
//            responseJson = StringEscapeUtils.unescapeJava(responseJson);
//            if (responseJson.contains("验证码错误")) return "";
//            return responseJson;
//            -------------------------------------------------------------------
            // 使用新接口
            String md5Pass = DigestUtil.md5Hex(user.getPassword());
            String loginString = Constants.getLoginUrl();
            loginString += "?username=" + user.getUsername() + "&password=" + md5Pass +  "&from=mobile";
            System.out.println(loginString);
            HttpGet httpGet = Request.httpGet(loginString);
            DefaultHttpClient httpClient = user.getHttpClient();
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String json = EntityUtils.toString(entity);
            json = StringEscapeUtils.unescapeJava(json);
            System.out.println(json);
            return json;



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
            if (login.contains("异常访问")) return "异常访问";
        }

        log.info(login);
        return "Success";
    }

}
