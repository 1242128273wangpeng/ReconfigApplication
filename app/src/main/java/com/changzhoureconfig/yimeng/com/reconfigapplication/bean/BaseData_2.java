package com.changzhoureconfig.yimeng.com.reconfigapplication.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class BaseData_2 {
    /**
     * status : 1
     * message : 提交成功
     * data : {}
     * token :
     */
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private String data;
    @SerializedName("token")
    private String token;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
