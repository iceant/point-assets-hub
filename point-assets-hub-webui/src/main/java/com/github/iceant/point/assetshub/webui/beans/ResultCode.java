package com.github.iceant.point.assetshub.webui.beans;

import com.github.iceant.point.assetshub.webui.utils.converter.StringToInteger;

public enum ResultCode {
    SUCCESS("200", "成功"),
    NOT_LOGIN("400", "没有登录"),
    EXCEPTION("401", "发生异常"),
    SYS_ERROR("402", "系统错误"),
    PARAMS_ERROR("403", "参数错误 "),
    NOT_SUPPORTED("410", "不支持或已经废弃"),
    INVALID_AUTHCODE("444", "无效的AuthCode"),
    TOO_FREQUENT("445", "太频繁的调用"),
    UNKNOWN_ERROR("499", "未知错误");

    private ResultCode(String value, String msg){
        this.val = value;
        this.msg = msg;
    }

    public String val() {
        return val;
    }

    public String msg() {
        return msg;
    }

    private String val;
    private String msg;

    public Integer code(){
        return new StringToInteger().convertFromAToB(val, -1);
    }
}