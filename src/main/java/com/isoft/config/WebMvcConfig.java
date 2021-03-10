package com.isoft.config;

import com.isoft.interceptors.URLInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 配置拦截器
     * 在springboot2.0.0之后，WebMvcConfigurerAdapter已经过时了
     * 使用WebMvcConfigurer或者WebMvcConfigurationSupport替代
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 如果有多个拦截器 按顺序配置即可
        registry.addInterceptor(new URLInterceptor())
                .addPathPatterns("/**");

    }

    /**
     * 解决跨域
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);

    }

}
