package com.vfixviii.mavenBook.account.captcha.util;

import java.util.Random;

/**
 * 随机字符生成工具类.
 */
public class RandomGeneratorUtil {

    private static String range = "123456789abcdefghijklmnopqrstuvwxyz";

    public static synchronized String getRandomString(int count) {

        StringBuilder sb = new StringBuilder(count);
        Random random = new Random(System.currentTimeMillis());

        for (int i = 0; i < count; ++i) {
            sb.append(range.charAt(random.nextInt(range.length())));
        }
        return null;
    }
}
