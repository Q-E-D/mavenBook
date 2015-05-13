package com.vfixviii.mavenBook.account.email.exception;

/**
 * 邮件处理异常类.
 * <p/>
 * 抛出统一的邮件处理异常，方便进行各种xx
 */
public class AccountEmailException extends Exception {

    /**
     * 邮件异常构造方法.
     *
     * @param s 异常消息
     */
    public AccountEmailException(final String s) {
        super(s);
    }
}
