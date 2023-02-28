package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.config.realm.*;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

    private static final String AUTHC = "authc";

    @Deprecated(forRemoval = true)
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(
            @Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/login-success");
        // ** means anything at least one
        // "authc" mwans needs auth, "anon" means anonymous
        // Must have error.html in advance
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/login-success", AUTHC);
        filterChainDefinitionMap.put("/register", "anon");
        filterChainDefinitionMap.put("/register-success", AUTHC);
        filterChainDefinitionMap.put("/index", AUTHC);
        filterChainDefinitionMap.put("/index/**", AUTHC);
        filterChainDefinitionMap.put("/**", AUTHC);

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Deprecated(forRemoval = true)
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("shiroRealm") ShiroRealm shiroRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);
        return securityManager;
    }

    @Bean(name = "lifecyclePostBeanProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public ShiroRealm shiroRealm() {
        return new ShiroRealm();
    }

    // Enable annotation mode
    @Deprecated(forRemoval = true)
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor attributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        attributeSourceAdvisor.setSecurityManager(securityManager);
        return attributeSourceAdvisor;
    }

}
