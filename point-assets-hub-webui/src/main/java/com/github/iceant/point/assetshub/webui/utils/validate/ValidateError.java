package com.github.iceant.point.assetshub.webui.utils.validate;

public class ValidateError {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public ValidateError setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ValidateError setMessage(String message) {
        this.message = message;
        return this;
    }
}
