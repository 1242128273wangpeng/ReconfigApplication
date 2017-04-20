package com.changzhoureconfig.yimeng.com.reconfigapplication.bean.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/3.
 */
public class LoginData implements Serializable {
    @SerializedName("resultMap")
    public LoginResultData resultMap;
    @SerializedName("msg")
    public String msg;//
    @SerializedName("resultCode")
    public String resultCode;//

    public LoginResultData getResultMap() {
        if (resultMap == null) {
            return null;
        } else {
            return resultMap;
        }

    }

    public void setResultMap(LoginResultData resultMap) {
        this.resultMap = resultMap;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "resultMap=" + resultMap +
                ",msg='" + msg + '\'' +
                ", resultCode='" + resultCode + '\'' +
                '}';
    }
}
