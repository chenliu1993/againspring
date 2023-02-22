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
import org.springframework.validation.BindingResult;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.web.bind.annotation.PathVariable;

// import lombok.extern.slf4j.Slf4j;

import com.example.demo.service.UserService;
import com.example.demo.service.UserRedisService;
import com.example.demo.domain.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@RequestMapping
// @Slf4j
@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @Autowired
    UserRedisService userRedisService;

    private User userPlaceholder = new User();

    @GetMapping
    @RequiresGuest
    public String translate(Model model) {
        logger.info("add user attribute into login model");
        User user = new User();
        model.addAttribute(user);
        return "redirect:/login";
    }

    @RequiresUser
    @GetMapping("login-success")
    public String loginSuccess() {
        logger.info("successfully logged in");
        return "login-success";
    }

    // Adding this then "Request Method GET NOt Allowd" goes away
    @GetMapping("login")
    @RequiresGuest
    public String preCheckLogin(@ModelAttribute("user") User user, Model model) {
        logger.info("seeing thr login page");
        return "login";
    }

    @PostMapping("login")
    @RequiresGuest
    public String login(@ModelAttribute("user") @Validated User user, BindingResult result, Model model) {
        // TODO: No use codes, just for testing model will remove one day
        UserEntity userEntity;
        try {
            userEntity = userService.findUserEntity(user.getName());
            logger.info(String.format("get user name %s with role %s, his password is %s", userEntity.getName(),
                    userEntity.getRole(), userEntity.getPassword()));
        } catch (NullPointerException e) {
            logger.error("no such user %s", user.getName());

            return "login";
        }

        if (result.hasErrors()) {
            logger.debug(String.format("has smoe issues when binding the user infomation %s", user.getName()));
            return "login";
        }
        Subject currentUser = SecurityUtils.getSubject();

        // add this line then any user can be logined in
        // if(!currentUser.isAuthenticated()){
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getName(), user.getPassword());
        usernamePasswordToken.setRememberMe(true);
        try {

            currentUser.login(usernamePasswordToken);
            logger.debug("user " + user.getName() + " login, yes");
        } catch (Exception e) {
            // We are not facing too much errors
            // e.printStackTrace();
            // register then, anybody can register
            logger.error("something went wrong with the user %s", user.getName());
            return "register";
        }
        // }

        return "redirect:/login-success";
    }

    // @GetMapping("register")
    // @RequiresGuest
    // public String bindEntity(Model model) {
    // UserEntity userEntity = new UserEntity();
    // model.addAttribute("userEntity", userEntity);
    // return "register";
    // }

    @PostMapping("register")
    @RequiresGuest
    public String register(@ModelAttribute("user") @Validated UserEntity userEntity, Model model) {
        // needs to reconstruct a new user and role to store back into db
        User user = new User();
        logger.info(userEntity.getName() + " " + userEntity.getPassword());
        user.setName(userEntity.getName());
        user.setPassword(userEntity.getPassword());

        Role role = new Role();
        logger.info(userEntity.getName() + " " + userEntity.getRole());
        role.setName(userEntity.getName());
        role.setRole(userEntity.getRole());

        userService.save(user);

        userRedisService.set(user.getName(), role.getRole());
        try {
            logger.info("try to save the user info");
            userService.saveRole(role);
        } catch (RuntimeException e) {
            return e.toString();
        }
        // A better way to do is?
        userPlaceholder = user;
        return "register-success";
    }

    @GetMapping("register-success")
    @RequiresUser
    public String afterRegister(Model model) {
        model.addAttribute("user", userPlaceholder);
        return "register-success";
    }

    @GetMapping("index")
    @RequiresUser
    @RequiresPermissions({ "all" })
    public String showUsers(Model model) {
        List<Role> roles = userService.findAll();
        model.addAttribute("users", roles);
        return "index";
    }

    @RequiresUser
    @RequiresPermissions({ "all" })
    @PostMapping("index/{name}")
    public String delete(@PathVariable String name) {
        logger.info("deleting the user %s", name);
        User user = userService.findOne(name);
        String roles = userService.findRole(name);
        Role role = new Role();
        role.setName(name);
        role.setRole(roles);
        userService.delete(user);
        userService.deleteRole(role);
        logger.info("user %s successfully deleted", name);
        return "redirect:/index";
    }
}
