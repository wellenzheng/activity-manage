package com.example.activitymanage.config;

import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.activitymanage.common.CommonResponse;
import com.example.activitymanage.model.AuthUser;
import com.example.activitymanage.response.AuthUserResponse;
import com.example.activitymanage.service.AuthUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhengweijun <zhengweijun@kuaishou.com>
 * Created on 2020-06-12
 */

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthUserService authUserService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authUserService);
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/v2/api-docs",
                "/configuration/ui",
                "/configuration/**",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login")
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter writer = httpServletResponse.getWriter();
                    AuthUser authUser = (AuthUser) authentication.getPrincipal();
                    String response = new ObjectMapper().writeValueAsString(CommonResponse
                            .success("登陆成功", AuthUserResponse.convert(authUser)));
                    writer.write(response);
                    writer.flush();
                    writer.close();
                })
                .failureHandler((httpServletRequest, httpServletResponse, exception) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter writer = httpServletResponse.getWriter();
                    CommonResponse commonResponse = CommonResponse.error("登陆失败", null);
                    if (exception instanceof LockedException) {
                        commonResponse.setMessage("账户被锁定");
                    } else if (exception instanceof CredentialsExpiredException) {
                        commonResponse.setMessage("密码已过期");
                    } else if (exception instanceof AccountExpiredException) {
                        commonResponse.setMessage("账户已过期");
                    } else if (exception instanceof DisabledException) {
                        commonResponse.setMessage("账户被禁用");
                    } else if (exception instanceof BadCredentialsException) {
                        commonResponse.setMessage("用户名或密码错误");
                    }
                    writer.write(new ObjectMapper().writeValueAsString(commonResponse));
                    writer.flush();
                    writer.close();
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter writer = httpServletResponse.getWriter();
                    if (authentication != null) {
                        AuthUser authUser = (AuthUser) authentication.getPrincipal();
                        writer.write(new ObjectMapper().writeValueAsString(CommonResponse
                                .success("注销成功", AuthUserResponse.convert(authUser))));
                    } else {
                        writer.write(new ObjectMapper().writeValueAsString(CommonResponse
                                .error("尚未登陆", null)));
                    }
                })
                .permitAll()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((httpServletRequest, httpServletResponse, exception) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter writer = httpServletResponse.getWriter();
                    CommonResponse response = CommonResponse.error("访问失败", null);
                    if (exception instanceof InsufficientAuthenticationException) {
                        response.setMessage("请求失败");
                    }
                    writer.write(new ObjectMapper().writeValueAsString(response));
                });
    }
}
