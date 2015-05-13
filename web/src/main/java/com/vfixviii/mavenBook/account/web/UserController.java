package com.vfixviii.mavenBook.account.web;

import com.vfixviii.mavenBook.account.service.dto.UserLoginDTO;
import com.vfixviii.mavenBook.account.service.dto.UserSignUpDTO;
import com.vfixviii.mavenBook.account.service.exception.AccountServiceException;
import com.vfixviii.mavenBook.account.service.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "sign", method = RequestMethod.POST)
    public String signUp(UserSignUpDTO dto, Model model) {
        try {
            service.signUp(dto);
        } catch (AccountServiceException e) {
            model.addAttribute("msg", e.getLocalizedMessage());
            return "error";
        }
        return "signSuccess";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(UserLoginDTO dto, Model model) {
        try {
            service.login(dto);
        } catch (AccountServiceException e) {
            model.addAttribute("msg", e.getLocalizedMessage());
            return "error";
        }
        return "loginSuccess";
    }

    @RequestMapping("activity/{name}/{acId}")
    public String activity(@PathVariable String name, @PathVariable String acId, Model model) {
        try {
            service.activity(name, acId);
        } catch (AccountServiceException e) {
            model.addAttribute("msg", e.getLocalizedMessage());
            return "error";
        }
        return "redirect:/";
    }
}