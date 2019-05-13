package com.bw.movie.data.bean;

/**
 * 系统读取状态的修改
 * 张娜
 */
public class UpdateReadMesBean {


    /**
     * message : 状态改变成功
     * status : 0000
     */

    private String message;
    private String status;

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
