package com.changzhoureconfig.yimeng.com.reconfigapplication.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 1 on 2017/2/23.
 */

public class AdvertImageData {

    /**
     * status : 1
     * message : 获取数据成功
     * data : [{"id":1,"imageName":"医院宣传","imageUrl":"http://58.215.75.134/jeeba/upload/images/welcomeImage/20170223/0f316b30-d3a1-4930-a03c-42594685317e60155.jpg","imageFlag":1,"ordNum":-1,"createTime":"2017-02-23 11:49:12","lastModifyTime":"2017-02-23 11:49:15"}]
     * token :
     */

    @SerializedName("status")
    public int status;
    @SerializedName("message")
    public String message;
    @SerializedName("token")
    public String token;
    @SerializedName("data")
    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 1
         * imageName : 医院宣传
         * imageUrl : http://58.215.75.134/jeeba/upload/images/welcomeImage/20170223/0f316b30-d3a1-4930-a03c-42594685317e60155.jpg
         * imageFlag : 1
         * ordNum : -1
         * createTime : 2017-02-23 11:49:12
         * lastModifyTime : 2017-02-23 11:49:15
         */

        @SerializedName("id")
        public int id;
        @SerializedName("imageName")
        public String imageName;
        @SerializedName("imageUrl")
        public String imageUrl;
        @SerializedName("imageFlag")
        public int imageFlag;
        @SerializedName("ordNum")
        public int ordNum;
        @SerializedName("createTime")
        public String createTime;
        @SerializedName("lastModifyTime")
        public String lastModifyTime;
    }
}
