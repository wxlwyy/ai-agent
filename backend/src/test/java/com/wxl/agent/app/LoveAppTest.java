package com.wxl.agent.app;

import cn.hutool.core.lang.UUID;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoveAppTest {

    @Resource
    private LoveApp loveApp;

    @Test
    void testChat() {
        String chatId = UUID.randomUUID().toString();
        // 第一轮
        String message = "你好，我是程序员李鱼皮";
        String answer = loveApp.doChat(message, chatId);
        // 第二轮
        message = "我想让另一半（他叫LYP）更爱我";
        answer = loveApp.doChat(message, chatId);
        Assertions.assertNotNull(answer);
        // 第三轮
        message = "我的职业是什么来着？刚跟你说过，帮我回忆一下";
        answer = loveApp.doChat(message, chatId);
        Assertions.assertNotNull(answer);
    }

    @Test
    void doChatWithReport() {
        String chatId = UUID.randomUUID().toString();
        String message = "你好，我是程序员鱼皮，我想让另一半LYP更爱我，但我不知道该怎么做";
        LoveApp.LoveReport loveReport = loveApp.doChatWithReport(message, chatId);
        Assertions.assertNotNull(loveReport);
    }

    @Test
    void doChatWithRag() {
        String chatId = UUID.randomUUID().toString();
        String message = "我已经结婚了，但是婚后关系不太亲密，怎么办？";
        String answer = loveApp.doChatWithRag(message, chatId);
        Assertions.assertNotNull(answer);
    }

    @Autowired
    @Qualifier("vectorStore")
    private VectorStore pgVectorStore;

    @Test
    void pgVectorVectorStore() {
        List<Document> documents = List.of(
                new Document("鱼皮的编程导航有什么用？学编程啊，做项目啊", Map.of("meta1", "meta1")),
                new Document("程序员鱼皮的原创项目教程 codefather.cn"),
                new Document("鱼皮这小伙子比较帅气", Map.of("meta2", "meta2")));
        // 添加文档
        pgVectorStore.add(documents);
        // 相似度查询
        List<Document> results = pgVectorStore.similaritySearch(
                SearchRequest.builder()
                        .query("怎么学编程啊")
                        .topK(3)
                        .build());
        Assertions.assertNotNull(results);
    }
}