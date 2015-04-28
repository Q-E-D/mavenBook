package com.vfixviii.mavenBook.account.email.service.impl;

import com.vfixviii.mavenBook.account.email.exception.EmailException;
import com.vfixviii.mavenBook.account.email.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

/**
 * 邮件服务实现类.
 */
@Service
public class EmailServiceImpl implements EmailService {

    /**
     * 邮件发送的Sender.
     */
    @Resource
    private JavaMailSender mailSender;

    /**
     * 系统的email.
     */
    @Value("${email.systemEmail}")
    private String systemEmail;

    @Override
    public final void sendEmail(final String emailAddr, final String title,
                                final String content) throws EmailException {

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
