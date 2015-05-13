package com.vfixviii.mavenBook.account.web;

import com.vfixviii.mavenBook.account.service.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * 用户前台控制器.
 */
@Controller
public class UserController {

    @Resource
    private AccountService service;

    @RequestMapping(value = "sign", method = RequestMethod.GET)
    public String signUp() {
        return "sign";
    }
}
