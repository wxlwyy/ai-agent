package com.wxl.agent.exception;

import com.wxl.agent.common.ErrorCode;
import lombok.Getter;

@Getter  //在全局异常处理器中使用
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private final int code;

    /**
     * 使用枚举类进行赋值
     */
    public BusinessException(ErrorCode errorCode) {   //在方法外通过构造方法给code和message赋值
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    /**
     * 使用枚举类给code赋值，灵活输入参数给message赋值
     */
    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }
}