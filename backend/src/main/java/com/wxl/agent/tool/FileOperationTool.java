package com.wxl.agent.tool;

import cn.hutool.core.io.FileUtil;
import com.wxl.agent.constant.FileConstant;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

/**
 * 文件操作工具类（提供文件读写功能）
 */
public class FileOperationTool {

    private final String FILE_DIR = FileConstant.FILE_SAVE_DIR + "/file";

    @Tool(description = "从文件中读取内容")
    public String readFile(@ToolParam(description = "要读取的文件名称") String fileName) {
        String filePath = FILE_DIR + "/" + fileName;
        try {
            return FileUtil.readUtf8String(filePath);
        } catch (Exception e) {
            return "Error reading file: " + e.getMessage();
        }
    }

    @Tool(description = "向文件中写入内容")
    public String writeFile(@ToolParam(description = "要写入的文件名") String fileName,
                            @ToolParam(description = "要写入的文件内容") String content
    ) {
        String filePath = FILE_DIR + "/" + fileName;

        try {
            // 创建目录（已创建就会自动跳过）
            FileUtil.mkdir(FILE_DIR);
            FileUtil.writeUtf8String(content, filePath);
            return "File written successfully to: " + filePath;
        } catch (Exception e) {
            return "Error writing to file: " + e.getMessage();
        }
    }
}