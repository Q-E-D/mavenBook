package com.vfixviii.mavenBook.account;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;
import com.vfixviii.mavenBook.account.email.exception.EmailException;
import com.vfixviii.mavenBook.account.email.service.EmailService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:*.xml")
public class EmailServiceTest {

    private GreenMail greenMail;

    @Resource
    private EmailService service;

    private String title;

    private String content;

    @Before
    public void startMailServer() {
        greenMail = new GreenMail(ServerSetup.SMTP);
        greenMail.setUser("vfixviii@mavenbook.com", "123456");
        greenMail.setUser("admin@mavenbook.com", "123456");
        greenMail.start();

        title = "hi";
        content = "<h1>hi</h1>";
    }

    @Test
    public void testSendMail() throws MessagingException, IOException, EmailException {

        service.sendEmail("vfixviii@mavenbook.com", title, content);

        greenMail.waitForIncomingEmail(2000, 1);

        Message[] msgs = greenMail.getReceivedMessages();
        assertEquals(1, msgs.length);
        assertEquals(title, msgs[0].getSubject());
        assertEquals(content, GreenMailUtil.getBody(msgs[0]).trim());
    }

    @Test(expected = EmailException.class)
    public void testSendMailException() throws EmailException {
        service.sendEmail("none@mavenbook.com", null, content);
    }

    @After
    public void stopMailServer() {
        greenMail.stop();
    }
}