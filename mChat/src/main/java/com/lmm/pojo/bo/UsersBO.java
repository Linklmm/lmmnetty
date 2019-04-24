package com.lmm.pojo.bo;

import javax.persistence.Column;
import javax.persistence.Id;

public class UsersBO {
    private String userId;
    private String faceData;
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFaceData() {
        return faceData;
    }

    public void setFaceData(String faceData) {
        this.faceData = faceData;
    }

    @Override
    public String toString() {
        return "UsersBO{" +
                "userId='" + userId + '\'' +
                ", faceData='" + faceData + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}