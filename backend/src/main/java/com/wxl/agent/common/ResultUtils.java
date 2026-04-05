package com.wxl.agent.common;

public class ResultUtils {

    /**
     * 成功（只有一种情况）
     *
     * @param data 数据
     * @param <T>  数据类型
     * @return 响应
     */
    public static <T> BaseResponse<T> success(T data) {  //第一个<T>代表定义静态方法的泛型，T是使用泛型
        return new BaseResponse<>(0, data, "ok");
    }

    /**
     * 失败（直接用枚举类传值）
     *
     * @param errorCode 错误码
     * @return 响应
     */
    public static BaseResponse<?> error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 失败（用枚举类传code，灵活传入message）
     *
     * @param errorCode 错误码
     * @return 响应
     */
    public static BaseResponse<?> error(ErrorCode errorCode, String message) {
        return new BaseResponse<>(errorCode.getCode(), null, message);
    }
}