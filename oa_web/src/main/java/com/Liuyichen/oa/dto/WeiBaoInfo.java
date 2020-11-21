package com.Liuyichen.oa.dto;

import com.Liuyichen.oa.entity.ClaimVoucher;
import com.Liuyichen.oa.entity.ClaimVoucherItem;
import com.Liuyichen.oa.entity.WeiBao;
import com.Liuyichen.oa.entity.WeiBaoItem;

import java.util.List;

public class WeiBaoInfo {
    private WeiBao weiBao;

    private List<WeiBaoItem> items;

    public void setWeiBao(WeiBao weiBao) {
        this.weiBao = weiBao;
    }

    public WeiBao getweiBao() {
        return weiBao;
    }

    public void setItems(List<WeiBaoItem> items) {
        this.items = items;
    }

    public List<WeiBaoItem> getItems() {
        return items;
    }
}
