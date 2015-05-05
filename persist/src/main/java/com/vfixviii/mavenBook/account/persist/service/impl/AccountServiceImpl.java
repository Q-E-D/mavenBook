package com.vfixviii.mavenBook.account.persist.service.impl;

import com.vfixviii.mavenBook.account.persist.entities.Account;
import com.vfixviii.mavenBook.account.persist.service.AccountService;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * 用户持久化服务实现类.
 */
@Service
public class AccountServiceImpl implements AccountService {

    private JdbcTemplate jdbcTemplate;

    @Resource
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Account account) {
        String sql = "INSERT INTO Account VALUES(NULL, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, account.getName(), account.getEmail(), account.getPassword(), account.getActivited());
    }

    @Override
    public void update(Account account) {
        String sql = "UPDATE Account VALUES(?, ?, ?, ?, ?) WHERE id = ?";
        jdbcTemplate.update(sql, account.getName(), account.getEmail(), account.getPassword(), account.getActivited(), account.getId());
    }

    @Override
    public Account get(Integer id) {
        String sql = "SELECT * FROM Account WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, Account.class);
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM ACCOUNT WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Account> findAll() {
        String sql = "SELECT * FROM ACCOUNT";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Account>(Account.class));
    }
}
