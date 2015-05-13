package com.vfixviii.mavenBook.account.service.service;

import com.vfixviii.mavenBook.account.service.dto.UserLoginDTO;
import com.vfixviii.mavenBook.account.service.dto.UserSignUpDTO;
import com.vfixviii.mavenBook.account.service.exception.AccountServiceException;

/**
 * 用户服务类，统一封装底层对外提供服务.
 */
public interface AccountService {

    /**
     * 生成验证码key
     */
    String generateCaptchaKey() throws AccountServiceException;

    /**
     * 生成验证码图片
     */
    byte[] generateCaptchaImage(String captchaKey) throws AccountServiceException;

    /**
     * 注册
     */
    void signUp(UserSignUpDTO dto) throws AccountServiceException;

    /**
     * 激活
     */
    void activity(String name, String activityKey) throws AccountServiceException;

    /**
     * 登陆
     */
    void login(UserLoginDTO dto) throws AccountServiceException;
}