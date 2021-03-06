package com.vfixviii.mavenBook.account.email.service;

import com.vfixviii.mavenBook.account.email.exception.AccountEmailException;

/**
 * 邮件发送服务类.
 */
public interface AccountEmailService {

    /**
     * 给指定地址发送邮件.
     *
     * @param emailAddr 目的email地址
     * @param title     标题
     * @param content   内容
     * @throws com.vfixviii.mavenBook.account.email.exception.AccountEmailException 邮件处理异常
     */
    void sendEmail(String emailAddr, String title, String content)
            throws AccountEmailException;
}
