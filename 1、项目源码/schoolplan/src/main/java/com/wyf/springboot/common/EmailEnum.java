package com.wyf.springboot.common;

public enum EmailEnum {
    FORGET_PASS(2);
    private Integer code;

    public Integer getCode() {
        return code;
    }

    EmailEnum(Integer code) {
        this.code = code;
    }
}
