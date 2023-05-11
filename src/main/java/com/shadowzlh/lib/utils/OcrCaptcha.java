package com.shadowzlh.lib.utils;

import com.shadowzlh.lib.common.Constants;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import java.awt.image.BufferedImage;

public class OcrCaptcha {
    public static String ocrCaptcha(BufferedImage bufferedImage) {
        String language = Constants.getLanguagePath().replace("%20"," ");
        ITesseract iTesseract = new Tesseract();


        // 设置训练库的位置
        iTesseract.setDatapath(language);

        iTesseract.setLanguage("eng");
        iTesseract.setVariable("tessedit_char_whitelist","0123456789");
        String result  = "";
        try {
             result = iTesseract.doOCR(bufferedImage);

        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
