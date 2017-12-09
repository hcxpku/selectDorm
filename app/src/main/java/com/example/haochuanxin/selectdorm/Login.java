package com.example.haochuanxin.selectdorm;

/**
 * Created by haochuanxin on 2017/11/29.
 */

public class Login {
    private  int errcode;
    private String data;

    public int getErrcode() {
        return errcode;
    }

    public String getData() {
        return data;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Login{" +
                "errcode=" + errcode +
                ", data='" + data + '\'' +
                '}';
    }
}
