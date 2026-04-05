package com.wxl.agent.common;

import lombok.Getter;

@Getter  //在其他方法中要获取code和message的值
public enum ErrorCode {

    SUCCESS(0, "ok"),
    PARAMS_ERROR(40000, "请求参数错误"),
    NOT_LOGIN_ERROR(40100, "未登录"),
    NO_AUTH_ERROR(40101, "无权限"),
    FORBIDDEN_ERROR(40300, "禁止访问"),
    NOT_FOUND_ERROR(40400, "请求数据不存在"),
    SYSTEM_ERROR(50000, "系统内部异常"),
    OPERATION_ERROR(50001, "操作失败"),
    AI_RESPONSE_ERROR(50010, "AI 响应异常"), // AI 服务器崩了或者返回了无法解析的 JSON。
    AI_QUOTA_ERROR(50011, "AI 额度不足"), // 你的 API Key 没钱了或者是触发了每分钟调用频率限制（Rate Limit）。
    CONTENT_SAFETY_ERROR(50012, "内容触发敏感词过滤"); // 用户提问或者 AI 回复触发了安全审查

    /**
     * 状态码
     */
    private final int code;  //防止通过枚举实例修改对应的属性

    /**
     * 信息
     */
    private final String message;

    ErrorCode(int code, String message) {  //默认就是私有的，只能在内部调用，统一在内部创造实例
        this.code = code;
        this.message = message;
    }

}