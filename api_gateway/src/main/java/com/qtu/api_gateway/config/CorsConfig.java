package com.qtu.api_gateway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Hu Shengkai
 * @create 2020-01-08 11:09
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        //允许任何请求的头部信息
        corsConfiguration.addAllowedHeader("*");
        //允许任何请求的方法
        corsConfiguration.addAllowedMethod("*");
        //允许任何请求域
        corsConfiguration.addAllowedOrigin("*");
        //允许客户端发送cookie等认证信息
        corsConfiguration.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }
}
