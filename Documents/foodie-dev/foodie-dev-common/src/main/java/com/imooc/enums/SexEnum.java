package com.imooc.enums;


/**
 * 性别枚举
 * @author CAIQIAN04
 */

public enum SexEnum {
    woman(0,"女"),
    man(1,"男"),
    secret(2,"保密");
    public final Integer type;
    public  final String Value;

    SexEnum(Integer type, String value) {
        this.type = type;
        Value = value;
    }
}
