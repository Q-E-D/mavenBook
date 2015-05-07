package com.vfixviii.mavenBook.account.captcha.service;

import com.vfixviii.mavenBook.account.captcha.exception.AccountCaptchaException;

import java.util.List;

/**
 * 验证码服务接口.
 */
public interface AccountCaptchaService {

    String generateCaptchaKey() throws AccountCaptchaException;

    byte[] generateCaptchaImage(String key) throws AccountCaptchaException;

    boolean valiateCaptcha(String key, String value) throws AccountCaptchaException;

    List<String> getPreDefinedTexts();

    void setPreDefinedTexts(List<String> preDefinedTexts);
}
