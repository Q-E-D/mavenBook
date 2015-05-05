package com.vfixviii.mavenBook.account.persist.service;

import com.vfixviii.mavenBook.account.persist.entities.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户持久化服务测试类.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:*.xml")
public class AccountServiceTest {

    @Resource
    private AccountService service;

    private Account account;

    @Before
    public void setup() {
        account = new Account();

        account.setName("test");
        account.setEmail("test@vfix.com");
        account.setPassword("123456");
        account.setActivited(true);
    }

    @Test
    public void testCURD() {
        Assert.assertNull(account.getId());
        service.save(account);

        List<Account> list = service.findAll();
        Assert.assertEquals(list.size(), 1);
    }
}
