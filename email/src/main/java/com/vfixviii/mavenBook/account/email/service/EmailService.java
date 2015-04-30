package com.vfixviii.mavenBook.account.email.service;

import com.vfixviii.mavenBook.account.email.exception.EmailException;

public interface EmailService {

    void sendEmail(String emailAddr, String title, String content) throws EmailException;
}
