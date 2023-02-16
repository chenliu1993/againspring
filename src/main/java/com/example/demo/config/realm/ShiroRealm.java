package com.example.demo.config.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {
    
    @Autowired
    private UserMapper userMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        
        User targetUser = userMapper.findOne(username);
       
        if(targetUser==null){
            throw new UnknownAccountException("No such user stored");
        }
        log.info("find user "+targetUser.getName());

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(targetUser.getName(), targetUser.getPassword(), getName());
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("do nothing here");
        return null;
    }

}