package com.mage.crm.base.exceptions;

/**
 * 自定义异常,需要抛出异常时抛出异常，，判定条件判断时，带参数抛出异常
 */
public class ParamException extends RuntimeException{

    public Integer code;
    public String msg;

    public ParamException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
