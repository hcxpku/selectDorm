package com.example.haochuanxin.selectdorm;

/**
 * Created by haochuanxin on 2017/12/8.
 */

public class information {
    private String id;
    private String name;
    private String gender;
    private String vcode;
    private String location;
    private String grade;
    private String room;
    private String building;

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "information{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", vcode='" + vcode + '\'' +
                ", location='" + location + '\'' +
                ", grade='" + grade + '\'' +
                ", room='" + room + '\'' +
                ", building='" + building + '\'' +
                '}';
    }
}
