package com.wxl.agent.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  //这里这个注解中用到了Component注解的功能，所以进行替换完全可以，这里只是为了语义
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 覆盖所有请求（也就是后端所有接口,意思：后端所有的接口（比如 /user/login, /picture/upload））
        registry.addMapping("/**")
        // 允许发送 Cookie
        .allowCredentials(true)
        /* 放行哪些域名， */
        .allowedOriginPatterns("http://localhost:5173") // 意思：允许特定的域名的前端来访问我。
        //.allowedOrigins("http://localhost:5173"); 
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // 意思：允许这些动作（查、增、改、删...）。
        .allowedHeaders("*") // 意思：前端请求头里带什么奇怪的东西（比如 token），我都接受。
        .exposedHeaders("*");  // 意思：我返回给前端的响应头，前端都能看。
    }
}