package com.Liuyichen.oa.dto;

import com.Liuyichen.oa.entity.ClaimVoucher;
import com.Liuyichen.oa.entity.ClaimVoucherItem;

import java.util.List;

public class ClaimVoucherInfo {
    private ClaimVoucher claimVoucher;

    private List<ClaimVoucherItem> items;

    public void setClaimVoucher(ClaimVoucher claimVoucher) {
        this.claimVoucher = claimVoucher;
    }

    public ClaimVoucher getClaimVoucher() {
        return claimVoucher;
    }

    public void setItems(List<ClaimVoucherItem> items) {
        this.items = items;
    }

    public List<ClaimVoucherItem> getItems() {
        return items;
    }
}
