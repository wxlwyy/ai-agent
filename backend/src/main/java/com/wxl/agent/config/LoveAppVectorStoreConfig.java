package com.wxl.agent.config;

import com.wxl.agent.rag.LoveAppDocumentLoader;
import com.wxl.agent.rag.MyKeywordEnricher;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 恋爱大师向量数据库配置（初始化基于内存的向量数据库 Bean）
 */
//@Configuration
public class LoveAppVectorStoreConfig {

//    @Resource
//    private MyTokenTextSplitter myTokenTextSplitter;


    @Bean("simpleVectorStore")  // @Bean 方法：默认用方法名作为 Bean 的名字。
    VectorStore loveAppSimVectorStore(EmbeddingModel dashscopeEmbeddingModel,
                                      LoveAppDocumentLoader loveAppDocumentLoader,
                                      MyKeywordEnricher myKeywordEnricher) {
        SimpleVectorStore simpleVectorStore = SimpleVectorStore.builder(dashscopeEmbeddingModel).build();
        // 加载文档
        List<Document> documentList = loveAppDocumentLoader.loadMarkdowns();
        // 自主切分文档
//        List<Document> splitDocuments = myTokenTextSplitter.splitCustomized(documentList);
        // 自动补充关键词元信息
        List<Document> enrichedDocuments = myKeywordEnricher.enrichDocuments(documentList);
//        simpleVectorStore.add(documentList);
        simpleVectorStore.add(enrichedDocuments);
        return simpleVectorStore;
    }
}