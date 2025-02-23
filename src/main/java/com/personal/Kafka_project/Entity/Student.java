package com.personal.Kafka_project.Entity;

public class Student {
    private Integer roll;
    private String name;
    private String fatherName;
    private String address;
    private String mobNo;
    private Integer[] marks = new Integer[5];

    private String status;

    public Student(){};

    public Student(Integer roll, String name, String fatherName, String address, String mobNo,Integer[] marks) {
        this.roll = roll;
        this.name = name;
        this.fatherName = fatherName;
        this.address = address;
        this.mobNo = mobNo;
        this.marks = marks;
    }

    public Integer getRoll() {
        return roll;
    }

    public void setRoll(Integer roll) {
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public Integer[] getMarks() {
        return marks;
    }

    public void setMarks(Integer[] marks) {
        this.marks = marks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
