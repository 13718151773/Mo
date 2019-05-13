package com.bw.movie.data.bean;

/**
 * 修改密码
 * 张娜
 */
public class UpdatePwdBean {


    /**
     * message : 密码修改成功
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
