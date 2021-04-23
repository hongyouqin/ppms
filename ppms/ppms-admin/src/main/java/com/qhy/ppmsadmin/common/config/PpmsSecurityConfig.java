package com.qhy.ppmsadmin.common.config;

import com.qhy.ppmssecurity.config.SecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class PpmsSecurityConfig extends SecurityConfig {

}