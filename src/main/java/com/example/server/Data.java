package com.example.server;

import java.io.Serializable;

public class Data implements Serializable {
    private long helloNum;
    private long byeNum;

    public long getHelloNum() {
        return helloNum;
    }

    public void setHelloNum(long helloNum) {
        this.helloNum = helloNum;
    }

    public long getByeNum() {
        return byeNum;
    }

    public void setByeNum(long byeNum) {
        this.byeNum = byeNum;
    }
}
