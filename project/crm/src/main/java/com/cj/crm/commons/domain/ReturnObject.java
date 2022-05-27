package com.cj.crm.commons.domain;

/**
 * 返回响应信息的对象
 *
 * @author 杰瑞
 * @date 2022/05/11
 */
public class ReturnObject {
    //处理成功或失败的标记 ，1--成功，0--失败
    private String code;
    //提示信息
    private String message;
    //其他数据
    private Object retData;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getRetData() {
        return retData;
    }

    public void setRetData(Object retData) {
        this.retData = retData;
    }

    @Override
    public String toString() {
        return "ReturnObject{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", retData=" + retData +
                '}';
    }
}
