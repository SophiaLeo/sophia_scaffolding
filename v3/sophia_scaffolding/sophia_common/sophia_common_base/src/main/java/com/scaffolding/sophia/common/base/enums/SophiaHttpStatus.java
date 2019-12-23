package com.scaffolding.sophia.common.base.enums;

import lombok.Getter;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.common.base.enums
 * @ClassName: SophiaHttpStatus
 * @Description: http状态枚举
 * @Version: 1.0
 */
@Getter
public enum SophiaHttpStatus {
    /**
     * Gl 000500 error code enum.
     */
    GL99990500(500, "未知异常"),
    /**
     * Gl 99990100 error code enum.
     */
    GL99990100(9999100, "参数异常"),
    /**
     * 成功
     * */
    SUCCESS(200,"成功"),
    /**
     * 未找到
     * */
    NOT_FOUND(404,"未找到 "),
    /**
     * 身份认证失败
     * */
    INVALID_TOKEN(401,"身份认证失败"),
    /**
     * 权限不足
     * */
    UNAUTHORIZED(403,"权限不足"),
    /**
     * 服务器异常
     * */
    SERVER_ERROR(500,"服务器异常"),
    /**
     * 失败
     * */
    COMMON_FAIL(-1,"失败"),
    /**
     * 该client_id不存在
     */
    CLIENT_ERROR(402, "该client_id不存在！"),
    /**
     * 服务器神游中
     */
    SERVER_FUGUE(666, "服务器神游中"),

    /**
     * 请求超时，请稍后再试！
     */
    SERVER_TIMEOUT(504, "请求超时，请稍后再试！"),

    /**
     * 服务调用失败
     * */
    HYSTRIX_ERROR(408, "调用xxx服务{}方法失败!");

    /**
     * 状态码
     * */
    private Integer code;
    /**
     * 消息
     * */
    private String message;

    SophiaHttpStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Gets enum.
     * @param code the code
     * @return the enum
     */
    public static SophiaHttpStatus getEnum(int code) {
        for (SophiaHttpStatus ele : SophiaHttpStatus.values()) {
            if (ele.getCode() == code) {
                return ele;
            }
        }
        return null;
    }
}
