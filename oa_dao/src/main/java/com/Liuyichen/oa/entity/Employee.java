package com.Liuyichen.oa.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Employee {
    private String sn;

    private String password;

    private String name;

    private String departmentSn;

    private String post;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date clocktime;

    private String cloack_status;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date clock_open_time;

    private int login_num;

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public String getSn() {
        return sn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDepartmentSn(String departmentSn) {
        this.departmentSn = departmentSn;
    }

    public String getPost() {
        return post;
    }

    public String getPassword() {
        return password;
    }

    public String getDepartmentSn() {
        return departmentSn;
    }

    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Date getClocktime() {
        return clocktime;
    }

    public void setClocktime(Date clocktime) {
        this.clocktime = clocktime;
    }

    public String getCloack_status() {
        return cloack_status;
    }

    public void setCloack_status(String cloack_status) {
        this.cloack_status = cloack_status;
    }

    public Date getClock_open_time() {
        return clock_open_time;
    }

    public void setClock_open_time(Date clock_open_time) {
        this.clock_open_time = clock_open_time;
    }

    public int getLogin_num() {
        return login_num;
    }

    public void setLogin_num(int login_num) {
        this.login_num = login_num;
    }
}
