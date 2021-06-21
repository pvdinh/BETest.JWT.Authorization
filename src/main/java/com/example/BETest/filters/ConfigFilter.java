package com.example.BETest.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigFilter {
    @Bean
    public FilterRegistrationBean<SimpleCorsFilter> corsFilter() {
        FilterRegistrationBean<SimpleCorsFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new SimpleCorsFilter());
        registrationBean.addUrlPatterns("/api/v1/*");
        return registrationBean;
    }

}
