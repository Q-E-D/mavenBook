package com.vfixviii.mavenBook.account.captcha.service.impl;

import com.vfixviii.mavenBook.account.captcha.exception.AccountCaptchaException;
import com.vfixviii.mavenBook.account.captcha.service.AccountCaptchaService;

import java.util.List;

/**
 * 验证码图片生成实现类.
 */
public class AccountCaptchaServiceImpl implements AccountCaptchaService {


    @Override
    public String generateCaptchaKey() throws AccountCaptchaException {
        return null;
    }

    @Override
    public byte[] generateCaptchaImage(String key) throws AccountCaptchaException {
        return new byte[0];
    }

    @Override
    public boolean valiateCaptcha(String key, String value) throws AccountCaptchaException {
        return false;
    }

    @Override
    public List<String> getPreDefinedTexts() {
        return null;
    }

    @Override
    public void setPreDefinedTexts(List<String> preDefinedTexts) {

    }
}
