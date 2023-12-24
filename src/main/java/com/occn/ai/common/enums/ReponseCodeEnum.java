package com.occn.ai.common.enums;

import lombok.Getter;


@Getter
public enum ReponseCodeEnum {

    BUSINESS_EXCEPTION(10010, "系统内部异常"),
    USER_NOTFOUND(10011, "用户不存在"),
    PASSWORD_FAIL(10012, "密码错误"),
    USER_EXISTS(10013, "用户已存在");

    private final int code;
    private final String message;

    ReponseCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
