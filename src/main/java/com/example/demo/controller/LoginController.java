package com.example.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.extern.slf4j.Slf4j;

import com.example.demo.service.UserService;
import com.example.demo.domain.User;

import java.util.*;

@RequestMapping
@Slf4j
@Controller
public class LoginController {
    
    @Autowired
    UserService userService;

    private User userPlaceholder = new User();

    @GetMapping
    @RequiresGuest
    public String translate(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "login";
    }

   
    @RequiresUser
    @GetMapping("login-success")
    public String loginSuccess() {
        return "login-success";
    }

    @PostMapping("login")
    @RequiresGuest
    public String login(@ModelAttribute("user") @Validated User user, Model model) {
        log.debug("get user "+user.getName());
        Subject currentUser = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getName(), user.getPassword());
        usernamePasswordToken.setRememberMe(true);
        try {
            currentUser.login(usernamePasswordToken);
            log.debug("user "+user.getName()+" login, yes");
        } catch(Exception e) {
            // We are not facing too much errors
            e.printStackTrace();
            return "register";
        }
        return "login-success";
    }

    @PostMapping("register")
    @RequiresGuest
    public String register(@ModelAttribute("user") @Validated User user, Model model) {
        userService.save(user);
        // A better way to do is?
        userPlaceholder = user;
        return "register-success";
    }

    @GetMapping("register-success")
    @RequiresUser
    public String afterRegister(Model model) {
        model.addAttribute(userPlaceholder);
        return "register-success";
    }

    @GetMapping("index")
    @RequiresUser
    @RequiresPermissions({"admin"})
    public String showUsers(Model model) {
        List<User> users = userService.findAll();

        model.addAttribute("users", users);
        return "index";
    }

    @RequiresUser
    @RequiresPermissions({"admin"})
	@DeleteMapping("index/{name}")
	public String delete(@PathVariable String name) {
        User user = userService.findOne(name);
		userService.delete(user);
		return "redirect:/index";
	}
}
