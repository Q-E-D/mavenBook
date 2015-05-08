package com.vfixviii.mavenBook.account.captcha.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 随机生成工具测试类.
 */
public class RandomGeneratorUtilTest {

    @Test
    public void randomStringTest() {

        List<String> list = new ArrayList<String>(100);
        for (int i = 0; i < 100; ++i) {
            String str = RandomGeneratorUtil.getRandomString(8);
            Assert.assertFalse(list.contains(str));
            list.add(str);
        }
    }
}
