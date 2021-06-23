package com.example.BETest.configs;

import com.example.BETest.filters.JwtLoginFilter;
import com.example.BETest.filters.SimpleCorsFilter;
import com.example.BETest.service.AccountService;
import com.example.BETest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AccountService accountService ;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
//                .antMatchers("/home").permitAll()
                .antMatchers("/login").permitAll()
//                .antMatchers(HttpMethod.POST,"/api/v1/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtLoginFilter("/login",authenticationManager()), UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(new JwtLoginFilter("/api/v1/login",authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new SimpleCorsFilter(),UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService);
    }

    //swagger config
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/images/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
}
