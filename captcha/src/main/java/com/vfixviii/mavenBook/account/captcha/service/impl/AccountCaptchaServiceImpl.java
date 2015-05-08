package com.vfixviii.mavenBook.account.captcha.service.impl;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.vfixviii.mavenBook.account.captcha.exception.AccountCaptchaException;
import com.vfixviii.mavenBook.account.captcha.service.AccountCaptchaService;
import com.vfixviii.mavenBook.account.captcha.util.RandomGeneratorUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 验证码图片生成实现类.
 */
@Service
public class AccountCaptchaServiceImpl implements AccountCaptchaService, InitializingBean {

    private DefaultKaptcha kaptcha;

    private List<String> preDefinedTexts;

    private Map<String, String> captchaMap = new HashMap<String, String>();

    private Integer index = 0;

    @Override
    public String generateCaptchaKey() throws AccountCaptchaException {
        String key = RandomGeneratorUtil.getRandomString(8);
        String text = getGenerateText();
        captchaMap.put(key, text);
        return key;
    }

    private String getGenerateText() {
        if (preDefinedTexts != null && !preDefinedTexts.isEmpty()) {
            return preDefinedTexts.get(index++ % preDefinedTexts.size());
        } else {
            return kaptcha.createText();
        }
    }

    @Override
    public byte[] generateCaptchaImage(String key) throws AccountCaptchaException {
        if (key == null) {
            throw new AccountCaptchaException("key不能为NULL！");
        } else {
            String text = captchaMap.get(key);
            if (text == null) {
                throw new AccountCaptchaException("验证码不存在");
            }
            BufferedImage img = kaptcha.createImage(text);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            try {
                ImageIO.write(img, "jpg", os);
            } catch (IOException e) {
                throw new AccountCaptchaException("生成验证码图片错误！" + e.getLocalizedMessage());
            }

            return os.toByteArray();
        }
    }

    @Override
    public boolean valiateCaptcha(String key, String value) throws AccountCaptchaException {
        if (key != null && value != null) {
            if (value.equals(captchaMap.get(key))) {
                captchaMap.remove(key);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public void setPreDefinedTexts(List<String> preDefinedTexts) {
        this.preDefinedTexts = preDefinedTexts;
    }

    @Override
    public void reset() {
        this.index = 0;
        this.captchaMap.clear();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.kaptcha = new DefaultKaptcha();
        this.kaptcha.setConfig(new Config(new Properties()));
    }
}