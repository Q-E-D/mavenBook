package com.vfixviii.mavenBook.account.persist.service;

import com.vfixviii.mavenBook.account.persist.entities.Account;

import java.util.List;

/**
 * 用户持久化服务器类.
 */
public interface AccountService {

    void save(Account account);

    void update(Account account);

    Account get(Integer id);

    void delete(Integer id);

    List<Account> findAll();
}