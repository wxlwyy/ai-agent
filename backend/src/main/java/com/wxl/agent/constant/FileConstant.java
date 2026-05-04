package com.wxl.agent.constant;

public final class FileConstant {
    // 私有构造，防止被 new
    private FileConstant() {}

    /* System.getProperty("user.dir") 是 Java 标准库提供的方法，专门用来获取 JVM 启动时的当前工作目录。
    * 就是你项目的根目录。比如你的项目路径是 /Users/yourname/Projects/ai-agent/backend，
    * 那么：System.getProperty("user.dir") 返回的就是 /Users/yourname/Projects/ai-agent/backend */
    /**
     * 文件保存目录
     */
    public static final String FILE_SAVE_DIR = System.getProperty("user.dir") + "/temp";
}