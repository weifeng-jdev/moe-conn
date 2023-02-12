package com.amano.moeconn.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public Result<T> success(String message) {
        this.message = message;
        this.code = 200;
        return this;
    }

    public static <T> Result<T> OK() {
        Result<T> r = new Result<T>();
        r.setCode(200);
        r.setMessage("成功");
        return r;
    }

    public static <T> Result<T> OK(T data) {
        Result<T> r = new Result<T>();
        r.setCode(200);
        r.setData(data);
        return r;
    }

    public static <T> Result<T> OK(String msg, T data) {
        Result<T> r = new Result<T>();
        r.setCode(200);
        r.setMessage(msg);
        r.setData(data);
        return r;
    }

    public static <T> Result<T> OK(int code, String msg, T data) {
        Result<T> r = new Result<T>();
        r.setCode(code);
        r.setMessage(msg);
        r.setData(data);
        return r;
    }

    public static Result<Object> error(String msg) {
        return error(500, msg);
    }

    public static Result<Object> error(int code, String msg) {
        Result<Object> r = new Result<Object>();
        r.setCode(code);
        r.setMessage(msg);
        return r;
    }

    public Result<T> error500(String message) {
        this.message = message;
        this.code = 500;
        return this;
    }

    public static Result<Object> noAuth(String msg) {
        return error(403, msg);
    }
}
