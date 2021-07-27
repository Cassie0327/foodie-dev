package com.imooc.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author CAIQIAN04
 */
@Configuration
public class CorsConfig {
    public CorsConfig()
    {

    }
    public org.springframework.web.filter.CorsFilter corsFilter()
    {
        //1. 添加cors配置信息
        CorsConfiguration config=new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:8080");
        // 设置是否发送cookie信息
        config.setAllowCredentials(true);
        // 设置允许请求的方式
        config.addAllowedMethod("*");
        //设置运行的header
        config.addAllowedHeader("*");

        // 2. 为url添加映射路径
        UrlBasedCorsConfigurationSource corsSource=new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**",config);
        // 3. 返回重新定义好的corsSource
        return new org.springframework.web.filter.CorsFilter(corsSource);
    }
}
