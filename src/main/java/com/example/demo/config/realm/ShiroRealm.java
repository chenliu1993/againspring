package com.example.demo.config.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.User;
import com.example.demo.mapper.*;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PolicyMapper policyMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();

        User targetUser = userMapper.findOne(username);

        if (targetUser == null) {
            throw new UnknownAccountException("No such user stored " + username);
        }
        log.info("find user " + targetUser.getName());

        return new SimpleAuthenticationInfo(targetUser.getName(), targetUser.getPassword(),
                getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        User targetUser = userMapper.findOne(username);
        Set<String> targetRole = new HashSet<>(Arrays.asList(roleMapper.findRole(username).split(",", -1)));

        Set<String> targetPolicy = new HashSet<>();
        for (Iterator<String> it = targetRole.iterator(); it.hasNext();) {
            String role = it.next();
            Set<String> singlePolicy = new HashSet<>(Arrays.asList(policyMapper.findPolicy(role)));
            targetPolicy.addAll(singlePolicy);
        }

        if (targetUser == null) {
            throw new UnknownAccountException("No such user stored " + username);
        }
        log.info("find user " + targetUser.getName());

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(targetRole);
        authorizationInfo.setStringPermissions(targetPolicy);
        return authorizationInfo;
    }

}