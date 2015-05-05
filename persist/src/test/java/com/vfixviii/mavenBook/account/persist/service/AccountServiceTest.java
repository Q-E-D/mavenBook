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

        service.save(account);
    }

    @Test
    public void testFinaAll() {
        List<Account> list = service.findAll();
        Assert.assertEquals(list.size(), 1);
    }

    @Test
    public void testGet() {
        Account ac = service.get(0);
        Assert.assertEquals(ac.getName(), account.getName());
        Assert.assertEquals(ac.getEmail(), account.getEmail());
        Assert.assertEquals(ac.getPassword(), account.getPassword());
        Assert.assertEquals(ac.getActivited(), account.getActivited());
        Assert.assertEquals(ac.getId(), Integer.valueOf(0));
    }

    @Test
    public void testUpdate() {
        Account ac = service.get(0);
        ac.setName("newName");
        service.update(ac);

        ac = service.get(0);
        Assert.assertEquals(ac.getName(), "newName");
    }

    @Test
    public void testDelete() {
        service.delete(0);

        List<Account> list = service.findAll();
        Assert.assertEquals(list.size(), 0);
    }
}
