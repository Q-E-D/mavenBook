package com.vfixviii.mavenBook.account.captcha.service;

import com.vfixviii.mavenBook.account.captcha.exception.AccountCaptchaException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 验证码服务测试类.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:*.xml")
public class AccountCaptchaServiceTest {

    @Resource
    private AccountCaptchaService service;

    @Test
    public void testGeneratorCaptcha() throws AccountCaptchaException, IOException {
        String key = service.generateCaptchaKey();
        byte[] img = service.generateCaptchaImage(key);
        Assert.assertTrue(img.length > 0);
        File file = new File("target/" + key + ".jpg");
        OutputStream os = new FileOutputStream(file);
        try {
            os.write(img);
        } finally {
            if (os != null) {
                os.close();
            }
        }
        Assert.assertTrue(file.exists() && file.length() > 0);
    }

    @Test
    public void testGeneratorValidateSuccess() throws AccountCaptchaException {
        List<String> preDefined = Arrays.asList("abc", "123");
        service.setPreDefinedTexts(preDefined);

        String key = service.generateCaptchaKey();
        service.generateCaptchaImage(key);
        Assert.assertTrue(service.valiateCaptcha(key, "abc"));

        key = service.generateCaptchaKey();
        service.generateCaptchaImage(key);
        Assert.assertTrue(service.valiateCaptcha(key, "123"));
    }

    @Test
    public void testGeneratorValidateFail() throws AccountCaptchaException {
        List<String> preDefined = Arrays.asList("efg");
        service.setPreDefinedTexts(preDefined);

        String key = service.generateCaptchaKey();
        service.generateCaptchaImage(key);
        Assert.assertFalse(service.valiateCaptcha(key, "123"));
    }

    @After
    public void reset() {
        service.reset();
        System.out.println("--");
    }
}