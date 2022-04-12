package com.example.project.domain;

/**
 * 结果消息
 *
 * @author 杰瑞
 * @date 2022/03/27
 */
public class ResultMessage {
    private boolean flag;
    private int situation;
    private String error_message;


    public ResultMessage() {
    }

    public ResultMessage(boolean flag, String error_message) {
        this.flag = flag;
        this.error_message = error_message;
    }

    public boolean isFlag() {
        return flag;
    }

    public int getSituation() {
        return situation;
    }

    public void setSituation(int situation) {
        this.situation = situation;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    @Override
    public String toString() {
        return "ResultMessage{" +
                "flag=" + flag +
                ", error_message='" + error_message + '\'' +
                '}';
    }
}
