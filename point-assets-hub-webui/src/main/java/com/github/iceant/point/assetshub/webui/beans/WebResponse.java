package com.github.iceant.point.assetshub.webui.beans;

public class WebResponse<T> {
    private T data;
    private int code;
    private String message;
    private String status;

    ////////////////////////////////////////////////////////////////////////////////
    ////
    public static <T> WebResponse<T> get(){
        return new WebResponse<>();
    }

    public static <T> WebResponse<T> makeSuccess(){
        return new WebResponse<T>().setCode(1).setStatus("success");
    }

    public static <T> WebResponse<T> makeFail(){
        return new WebResponse<T>().setCode(-1).setStatus("fail");
    }

    ////////////////////////////////////////////////////////////////////////////////
    ////

    public T getData() {
        return data;
    }

    public WebResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    public WebResponse<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public WebResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public WebResponse<T> setStatus(String status) {
        this.status = status;
        return this;
    }
}