package com.vfixviii.mavenBook.account.email;

/**
 * Created by Administrator on 2015/4/27.
 */
public interface EmailService {

    void sendEmail(String emailAddr, String title, String content);
}
