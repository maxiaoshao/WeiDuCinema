package com.example.weiducinema.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by 邵新轩(轩少)
 * function:
 * on 2019/1/24
 */

public class Login_Bean {

    private int userId;
    private String nickName;
    private String phone;
    private String pwd;
    private String sessionId;
    private String userName;
    private int whetherVip;
    private int whetherFaceId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getWhetherVip() {
        return whetherVip;
    }

    public void setWhetherVip(int whetherVip) {
        this.whetherVip = whetherVip;
    }

    public int getWhetherFaceId() {
        return whetherFaceId;
    }

    public void setWhetherFaceId(int whetherFaceId) {
        this.whetherFaceId = whetherFaceId;
    }
}
