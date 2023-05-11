package com.shadowzlh.lib.service;

import com.shadowzlh.lib.common.Constants;
import com.shadowzlh.lib.common.Request;
import com.shadowzlh.lib.domain.User;
import com.shadowzlh.lib.utils.OcrCaptcha;
import com.shadowzlh.lib.utils.PNG2JPG;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

@Slf4j
public class Captcha {

    public static String getCaptcha(User user){

        DefaultHttpClient httpClient = user.getHttpClient();

        HttpGet httpGet = Request.httpGet(Constants.getCaptchaUrl());

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();

//            输出验证码图片
            /*转化为字节输出流*/
            user.setPngBytes(EntityUtils.toByteArray(entity));
            return "Success";

        }catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }

    }

    public static String getNormativeCaptcha(User user){

            while (getCaptcha(user).equals("Error"));

            String captcha = OcrCaptcha.ocrCaptcha(PNG2JPG.png2jpg(user));;

            captcha = captcha.replace(" ","");
            captcha = captcha.replace("\n","");
            while (captcha.length() != 4) {
                captcha = getNormativeCaptcha(user);
            }
            log.info(captcha);
            return captcha;
    }
}
