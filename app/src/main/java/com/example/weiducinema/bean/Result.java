package com.example.weiducinema.bean;

/**
 * created by fxb
 * 2019/1/23 14:25
 */
public class Result<T> {
    private T result;
    private String message;
    private String status;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
