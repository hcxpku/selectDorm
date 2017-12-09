package com.example.haochuanxin.selectdorm;

/**
 * Created by haochuanxin on 2017/12/9.
 */

public class totalMan {
    private String name;
    private String num1;
    private String vcode1;
    private String num2;
    private String vcode2;
    private String num3;
    private String vcode3;
    private int buildingNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getVcode1() {
        return vcode1;
    }

    public void setVcode1(String vcode1) {
        this.vcode1 = vcode1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public String getVcode2() {
        return vcode2;
    }

    public void setVcode2(String vcode2) {
        this.vcode2 = vcode2;
    }

    public String getNum3() {
        return num3;
    }

    public void setNum3(String num3) {
        this.num3 = num3;
    }

    public String getVcode3() {
        return vcode3;
    }

    public void setVcode3(String vcode3) {
        this.vcode3 = vcode3;
    }

    public int getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(int buildingNo) {
        this.buildingNo = buildingNo;
    }

    @Override
    public String toString() {
        return "totalMan{" +
                "name='" + name + '\'' +
                ", num1='" + num1 + '\'' +
                ", vcode1='" + vcode1 + '\'' +
                ", num2='" + num2 + '\'' +
                ", vcode2='" + vcode2 + '\'' +
                ", num3='" + num3 + '\'' +
                ", vcode3='" + vcode3 + '\'' +
                ", buildingNo=" + buildingNo +
                '}';
    }
}
