package com.shadowzlh.lib.utils;

import com.shadowzlh.lib.domain.User;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class PNG2JPG {
    public static BufferedImage png2jpg(User user)  {
        try {
            BufferedImage bufferedImage;
            InputStream is  = new ByteArrayInputStream(user.getPngBytes());
            bufferedImage = ImageIO.read(is);
            BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
                    bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            //由于png格式是32位，jpg格式是24位，因此要进行位数转换
            //TYPE_INT_RGB:创建一个RBG图像，24位深度，成功将32位图转化成24位
            newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
//            ImageIO.write(newBufferedImage, "jpg", new File(Constants.jpgCaptchaPath));
            return newBufferedImage;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
