package com.imooc.enums;

public enum CommentLevel {

    GOOD(1, "good comments"),
    NORMAL(2, "normal comments"),
    BAD(3, "bad comments");

    public final Integer type;
    public final String value;

    CommentLevel(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
