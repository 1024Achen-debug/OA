package com.Liuyichen.oa.entity;

public class WeiBaoItem {
    private Integer id;

    private Integer weiBaoId;

    private String item;

    private Double amount;

    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWeiBaoIdId() {
        return weiBaoId;
    }

    public void setClaimVoucherId(Integer claimVoucherId) {
        this.weiBaoId = claimVoucherId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
