package com.qhy.ppmsadmin.common.config;

import com.qhy.ppmsadmin.service.AdminService;
import com.qhy.ppmssecurity.config.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class PpmsSecurityConfig extends SecurityConfig {
    @Autowired
    private AdminService adminService;

    @Bean
    public UserDetailsService userDetailsService() {
        // 获取登录用户信息
        return username -> adminService.loadUserByEmail(username);
    }
}