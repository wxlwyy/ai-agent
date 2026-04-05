package com.wxl.agent.demo.invoke;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 手动挡：使用 Hutool 直接通过 HTTP 调用大模型
 */
public class HttpAiInvoke {

    public static void main(String[] args) {
        // 1. 定义请求地址
        String url = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";

        // 2. 填写你的 API Key（记得替换成你真实的 sk-xxx）
        String apiKey = TestApiKey.API_KEY;

        // 3. 构建请求体 (对照你的 CURL 命令手动拼装 JSON 结构)
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("model", "qwen-plus");

        // 构建 input 部分
        Map<String, Object> inputMap = new HashMap<>();
        List<Map<String, String>> messages = new ArrayList<>();
        
        messages.add(Map.of("role", "system", "content", "You are a helpful assistant."));
        messages.add(Map.of("role", "user", "content", "你是谁？"));
        
        inputMap.put("messages", messages);
        bodyMap.put("input", inputMap);

        // 构建 parameters 部分
        Map<String, String> parametersMap = new HashMap<>();
        parametersMap.put("result_format", "message");
        bodyMap.put("parameters", parametersMap);

        // 4. 发送 POST 请求
        String result = HttpRequest.post(url)
                .header(Header.AUTHORIZATION, "Bearer " + apiKey) // 设置鉴权头
                .header(Header.CONTENT_TYPE, "application/json")   // 设置内容类型
                .body(JSONUtil.toJsonStr(bodyMap))                // 将 Map 转为 JSON 字符串
                .execute()
                .body();

        // 5. 打印结果
        System.out.println("AI 响应结果：\n" + result);
    }
}