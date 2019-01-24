package com.example.weiducinema.core.exception;

/**
 * Created by 邵新轩(轩少)
 * function:
 * on 2019/1/3
 */
public class ApiException extends Exception {

    private int code;

    private String displayMessage;



    public ApiException(int code, String displayMessage) {

        this.code = code;

        this.displayMessage = displayMessage;

    }



    public ApiException(int code, String message, String displayMessage) {

        super(message);

        this.code = code;

        this.displayMessage = displayMessage;

    }



    public int getCode() {

        return code;

    }



    public void setCode(int code) {

        this.code = code;

    }



    public String getDisplayMessage() {

        return displayMessage;

    }



    public void setDisplayMessage(String displayMessage) {

        this.displayMessage = displayMessage;

    }

}
