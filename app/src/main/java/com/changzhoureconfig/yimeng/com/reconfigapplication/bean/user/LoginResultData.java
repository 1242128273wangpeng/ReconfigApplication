package com.changzhoureconfig.yimeng.com.reconfigapplication.bean.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/3.
 */
public class LoginResultData implements Serializable {
    @SerializedName("welfareTime")
    public String welfareTime;//享受福利的时间
    @SerializedName("user")
    public user user;
    @SerializedName("status")
    public int status;// 0为不显示 1为显示
    @SerializedName("remindCount")
    public String remindCount;

    public String getRemindCount() {
        return remindCount;
    }

    public int getStatus() {
        return status;
    }

    public String getWelfareTime() {
        return welfareTime;
    }

    public LoginResultData.user getUser() {
        return user;
    }

    public class user {
        @SerializedName("id")
        public int id;
        @SerializedName("statusCase")
        public int statusCase;//为0 跳验证界面 为1不跳
        @SerializedName("uid")
        public String uid;// 唯一识别码
        @SerializedName("age")
        public String age;//年龄
        @SerializedName("flag")
        public String flag;// 孕妇状态 1:孕前 2：孕期中 3：产后
        @SerializedName("phone")
        public String phone;//手机号
        @SerializedName("nickname")
        public String nickname;//昵称
        @SerializedName("name")
        public String name;//真实姓名
        @SerializedName("password")
        public String password;//密码
        @SerializedName("headurl")
        public String headurl;//头像地址
        @SerializedName("qq")
        public String qq;//QQ号
        @SerializedName("pregnancy")
        public String pregnancy;//用户设置预产期
        @SerializedName("dueDate")
        public String dueDate;//预产期
        @SerializedName("only_score")
        public String only_score;// 个人获得积分
        @SerializedName("recom_score")
        public String recom_score;//邀请获得积分
        @SerializedName("sum_score")
        public String sum_score;//总积分
        @SerializedName("check_sum")
        public String check_sum;//签到次数
        @SerializedName("address")
        public String address; //地址
        @SerializedName("sex")
        public String sex;// 性别
        @SerializedName("share")
        public String share;// 0为显示 分享加积分
        @SerializedName("perfect")
        public String perfect;// 0为显示 完善资料
        @SerializedName("check_time")
        public String check_time;//上次签到时间
        @SerializedName("sysPregnancy")
        public String sysPregnancy;//医生设置预产期
        @SerializedName("birthday")
        public String birthday;//生日
        @SerializedName("registerFlag")
        public String registerFlag;//0为第一次登陆

        public void setRegisterFlag(String registerFlag) {
            this.registerFlag = registerFlag;
        }

        public String getRegisterFlag() {
            return registerFlag;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getPregnancy() {
            return pregnancy;
        }

        public String getCheck_sum() {
            return check_sum;
        }

        public void setCheck_sum(String check_sum) {
            this.check_sum = check_sum;
        }

        public String getCheck_time() {
            return check_time;
        }

        public void setCheck_time(String check_time) {
            this.check_time = check_time;
        }

        public String getOnly_score() {
            return only_score;
        }

        public void setOnly_score(String only_score) {
            this.only_score = only_score;
        }

        public String getRecom_score() {
            return recom_score;
        }

        public void setRecom_score(String recom_score) {
            this.recom_score = recom_score;
        }

        public String getSum_score() {
            return sum_score;
        }

        public void setSum_score(String sum_score) {
            this.sum_score = sum_score;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getShare() {
            return share;
        }

        public void setShare(String share) {
            this.share = share;
        }

        public String getPerfect() {
            return perfect;
        }

        public void setPerfect(String perfect) {
            this.perfect = perfect;
        }

        public String getDueDate() {
            return dueDate;
        }

        public void setDueDate(String dueDate) {
            this.dueDate = dueDate;
        }

        public int getStatusCase() {
            return statusCase;
        }

        public void setStatusCase(int statusCase) {
            this.statusCase = statusCase;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getSysPregnancy() {
            return sysPregnancy;
        }

        public void setSysPregnancy(String sysPregnancy) {
            this.sysPregnancy = sysPregnancy;
        }

        public void setPregnancy(String pregnancy) {
            this.pregnancy = pregnancy;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getHeadurl() {
            return headurl;
        }

        public void setHeadurl(String headurl) {
            this.headurl = headurl;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        @Override
        public String toString() {
            return "user{" +
                    "id=" + id +
                    ", statusCase=" + statusCase +
                    ", uid='" + uid + '\'' +
                    ", age='" + age + '\'' +
                    ", flag='" + flag + '\'' +
                    ", phone='" + phone + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", name='" + name + '\'' +
                    ", password='" + password + '\'' +
                    ", headurl='" + headurl + '\'' +
                    ", qq='" + qq + '\'' +
                    ", pregnancy='" + pregnancy + '\'' +
                    ", dueDate='" + dueDate + '\'' +
                    ", only_score='" + only_score + '\'' +
                    ", recom_score='" + recom_score + '\'' +
                    ", sum_score='" + sum_score + '\'' +
                    ", check_sum='" + check_sum + '\'' +
                    ", address='" + address + '\'' +
                    ", sex='" + sex + '\'' +
                    ", share='" + share + '\'' +
                    ", perfect='" + perfect + '\'' +
                    ", check_time='" + check_time + '\'' +
                    ", sysPregnancy='" + sysPregnancy + '\'' +
                    ", birthday='" + birthday + '\'' +
                    ", registerFlag='" + registerFlag + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LoginresultData{" +
                "welfareTime='" + welfareTime + '\'' +
                ", user=" + user +
                ", status=" + status +
                '}';
    }
}
