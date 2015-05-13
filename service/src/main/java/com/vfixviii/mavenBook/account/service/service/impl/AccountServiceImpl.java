package com.vfixviii.mavenBook.account.service.service.impl;

import com.vfixviii.mavenBook.account.captcha.exception.AccountCaptchaException;
import com.vfixviii.mavenBook.account.captcha.service.AccountCaptchaService;
import com.vfixviii.mavenBook.account.email.exception.AccountEmailException;
import com.vfixviii.mavenBook.account.email.service.AccountEmailService;
import com.vfixviii.mavenBook.account.persist.entities.Account;
import com.vfixviii.mavenBook.account.persist.service.AccountPersistService;
import com.vfixviii.mavenBook.account.service.dto.UserLoginDTO;
import com.vfixviii.mavenBook.account.service.dto.UserSignUpDTO;
import com.vfixviii.mavenBook.account.service.exception.AccountServiceException;
import com.vfixviii.mavenBook.account.service.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 用户服务实现类.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountCaptchaService captchaService;

    @Resource
    private AccountEmailService emailService;

    @Resource
    private AccountPersistService persistService;

    private Map<String, String> activityMap = new HashMap<String, String>();

    @Override
    public String generateCaptchaKey() throws AccountServiceException {
        try {
            return captchaService.generateCaptchaKey();
        } catch (AccountCaptchaException e) {
            throw new AccountServiceException(e.getLocalizedMessage());
        }
    }

    @Override
    public byte[] generateCaptchaImage(String captchaKey) throws AccountServiceException {
        try {
            return captchaService.generateCaptchaImage(captchaKey);
        } catch (AccountCaptchaException e) {
            throw new AccountServiceException(e.getLocalizedMessage());
        }
    }

    @Override
    public void signUp(UserSignUpDTO dto) throws AccountServiceException {
        persistService.save(new Account(dto.getName(), dto.getEmail(), dto.getPassword()));
        String uuid = UUID.randomUUID().toString();
        activityMap.put(dto.getName(), uuid);
        try {
            emailService.sendEmail(dto.getName(), "激活", "http://nixi.im/activity/" + uuid);
        } catch (AccountEmailException e) {
            throw new AccountServiceException(e.getLocalizedMessage());
        }
    }

    @Override
    public void activity(String name, String activityKey) {
        Account account = persistService.getByName(name);
        if (activityKey.equals(activityMap.get(name))) {
            activityMap.remove(name);
            account.setActivited(true);
            persistService.update(account);
        }
    }

    @Override
    public void login(UserLoginDTO dto) throws AccountServiceException {
        Account account = persistService.getByName(dto.getUsername());
        if (account != null) {
            if (account.getActivited()) {
                if (account.getName().equals(dto.getUsername()) && account.getPassword().equals(dto.getPassword())) {
                    return;
                } else {
                    throw new AccountServiceException("用户名或者密码错误！");
                }
            } else {
                throw new AccountServiceException("用户未激活！");
            }
        } else {
            throw new AccountServiceException("用户不存在或用户名密码错误！");
        }
    }
}