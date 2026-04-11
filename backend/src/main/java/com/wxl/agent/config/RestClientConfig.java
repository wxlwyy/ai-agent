package com.wxl.agent.config;

import org.springframework.boot.web.client.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClientCustomizer restClientCustomizer() {
        return builder -> {
            SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
            // 建立连接的超时时间设为 10 秒
            factory.setConnectTimeout(10000); 
            // 等待响应的读取超时时间设为 60 秒（解决大模型推理耗时报错的关键）
            factory.setReadTimeout(60000); 
            
            builder.requestFactory(factory);
        };
    }
}

/*
旧写法（笨重）：在 Java 8 以前，你得写一大堆代码来“实现”这个RestClientCustomizer接口：

return new RestClientCustomizer() {
    @Override
    public void customize(RestClient.Builder builder) {
        // 这里写逻辑...
    }
};*/
