package com.vfixviii.mavenBook.account.persist.service;

import com.vfixviii.mavenBook.account.persist.entities.Account;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 用户持久化服务测试类.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:*.xml")
public class AccountPersistServiceTest {

    @Resource
    private AccountPersistService service;

    @Resource
    private DataSource dataSource;

    private Account account;

    private Connection conn;

    @Before
    public void setup() throws SQLException {
        account = new Account();

        account.setName("test");
        account.setEmail("test@vfix.com");
        account.setPassword("123456");
        account.setActivited(true);

        service.save(account);

        conn = dataSource.getConnection();
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

    @After
    public void reset() throws SQLException {
        conn.createStatement().execute("TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK");
    }
}