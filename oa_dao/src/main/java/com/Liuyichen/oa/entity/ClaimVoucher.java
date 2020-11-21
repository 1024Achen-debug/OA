package com.Liuyichen.oa.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ClaimVoucher {
    private Integer id;

    private String cause;

    private String createSn;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date createTime;

    private String nextDealSn;

    private Double totalAmount;

    private String status;

    public Date getCreateTime() {
        return createTime;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Integer getId() {
        return id;
    }

    public String getCause() {
        return cause;
    }

    public String getCreateSn() {
        return createSn;
    }

    public String getNextDealSn() {
        return nextDealSn;
    }

    public String getStatus() {
        return status;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public void setCreateSn(String createSn) {
        this.createSn = createSn;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNextDealSn(String nextDealSn) {
        this.nextDealSn = nextDealSn;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    private Employee creater;
    private Employee dealer;

    public Employee getCreater() {
        return creater;
    }

    public Employee getDealer() {
        return dealer;
    }

    public void setCreater(Employee creater) {
        this.creater = creater;
    }

    public void setDealer(Employee dealer) {
        this.dealer = dealer;
    }
}
