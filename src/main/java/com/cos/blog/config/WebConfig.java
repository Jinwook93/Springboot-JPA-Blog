package com.cos.blog.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 특정 URL 경로에 대해 JSP 매핑 설정
        registry.addViewController("/views/**").setViewName("forward:/WEB-INF/views/");
    }
}
