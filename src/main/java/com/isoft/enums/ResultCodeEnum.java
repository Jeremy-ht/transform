package com.isoft.enums;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {

    SUCCESS(20000, "操作成功"),
    FAILED(3000, "响应失败"),

    VALIDATE_FAILED(1002, "参数校验失败"),
    ERROR(5000, "未知错误");

    private Integer code;
    private String msg;

    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
