package com.wxl.agent.initializer;

import com.wxl.agent.rag.LoveAppDocumentLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component  // ApplicationRunner 会在 Spring 容器完全启动后执行，处理这类启动任务更加安全、规范。
public class KnowledgeBaseInitializer implements ApplicationRunner {

    // 1. 属性移到这里，和这个类内聚
    @Value("${app.init-vectorstore:false}")
    private boolean shouldInit;

    // 2. 注入需要的依赖
    private final LoveAppDocumentLoader documentLoader;
    private final VectorStore vectorStore;

    public KnowledgeBaseInitializer(LoveAppDocumentLoader documentLoader,
                                    @Qualifier("vectorStore") VectorStore pgVectorStore) {
        this.documentLoader = documentLoader;
        this.vectorStore = pgVectorStore;
    }

    // 3. 用 ApplicationRunner 的 run 方法代替 @PostConstruct
    @Override
    public void run(ApplicationArguments args) {
        if (shouldInit) {
            log.info(">>> 检测到开关开启，开始分批初始化知识库...");
            List<Document> allDocs = documentLoader.loadMarkdowns();

            int step = 10;
            for (int i = 0; i < allDocs.size(); i += step) {
                int end = Math.min(i + step, allDocs.size());
                List<Document> batch = allDocs.subList(i, end);

                log.info(">>> 正在处理第 {} 到 {} 条数据", i, end);
                vectorStore.add(batch);
            }
            log.info(">>> 知识库初始化完成！");
        } else {
            log.info(">>> 知识库初始化开关关闭，跳过导入。");
        }
    }
}