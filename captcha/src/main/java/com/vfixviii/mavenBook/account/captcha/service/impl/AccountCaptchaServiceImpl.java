package com.vfixviii.mavenBook.account.captcha.service.impl;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.vfixviii.mavenBook.account.captcha.exception.AccountCaptchaException;
import com.vfixviii.mavenBook.account.captcha.service.AccountCaptchaService;
import com.vfixviii.mavenBook.account.captcha.util.RandomGeneratorUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

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

    private Map<String, String> captchaMap;

    private Integer index = 0;

    @Override
    public String generateCaptchaKey() throws AccountCaptchaException {
        String key = RandomGeneratorUtil.getRandomString(8);
        String text = getGenerateText(key);
        captchaMap.put(key, text);

        return text;
    }

    private String getGenerateText(String key) {
        if (preDefinedTexts != null && !preDefinedTexts.isEmpty()) {
            return preDefinedTexts.get(index++ % preDefinedTexts.size());
        } else {
            return kaptcha.createText();
        }
    }

    @Override
    public byte[] generateCaptchaImage(String key) throws AccountCaptchaException {
        return new byte[0];
    }

    @Override
    public boolean valiateCaptcha(String key, String value) throws AccountCaptchaException {
        if (key != null && value != null) {
            return value.equals(captchaMap.get(key));
        }
        return false;
    }

    @Override
    public List<String> getPreDefinedTexts() {
        return this.preDefinedTexts;
    }

    @Override
    public void setPreDefinedTexts(List<String> preDefinedTexts) {
        this.setPreDefinedTexts(preDefinedTexts);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.kaptcha = new DefaultKaptcha();
        this.kaptcha.setConfig(new Config(new Properties()));
    }
}