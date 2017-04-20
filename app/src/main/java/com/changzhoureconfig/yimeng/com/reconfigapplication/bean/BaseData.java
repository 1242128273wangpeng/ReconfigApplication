package com.changzhoureconfig.yimeng.com.reconfigapplication.bean;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class BaseData {
    private String resultCode;
    private String msg;
    private resultMap resultMap;

    public String getResultCode() {
        return resultCode;
    }

    public String getMsg() {
        return msg;
    }

    public BaseData.resultMap getResultMap() {
        return resultMap;
    }

    private class resultMap {
        @Override
        public String toString() {
            return super.toString();
        }
    }

    @Override
    public String toString() {
        return "BaseData{" +
                "resultCode='" + resultCode + '\'' +
                ", msg='" + msg + '\'' +
                ", resultMap='" + resultMap + '\'' +
                '}';
    }
}
