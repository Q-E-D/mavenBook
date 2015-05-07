package com.vfixviii.mavenBook.account.captcha;

import com.vfixviii.mavenBook.account.captcha.service.AccountCaptchaService;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 验证码服务测试类.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:*.xml")
public class AccountCaptchaServiceTest {

    @Resource
    private AccountCaptchaService service;


}