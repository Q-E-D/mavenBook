package com.vfixviii.mavenBook.account.email.service.impl;

import com.vfixviii.mavenBook.account.email.exception.EmailException;
import com.vfixviii.mavenBook.account.email.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Resource
    private JavaMailSender mailSender;

    @Value("${email.systemEmail}")
    private String systemEmail;

    @Override
    public void sendEmail(String emailAddr, String title, String content) throws EmailException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(systemEmail);
            helper.setTo(emailAddr);
            helper.setSubject(title);
            helper.setText(content, true);
        } catch (Exception e) {
            throw new EmailException("邮件发送失败！");
        }

        mailSender.send(message);
    }
}