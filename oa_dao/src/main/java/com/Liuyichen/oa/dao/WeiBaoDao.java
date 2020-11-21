package com.Liuyichen.oa.dao;

import com.Liuyichen.oa.entity.ClaimVoucher;
import com.Liuyichen.oa.entity.WeiBao;
import com.Liuyichen.oa.entity.WeiBaoItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("weiBaoDao")
public interface WeiBaoDao {
    void insert(WeiBao weiBao);

    void update(WeiBao weiBao);

    void delete(int id);

    WeiBao select(int id);

    List<WeiBao> selectByCreateSn(String csn);

    List<WeiBao> selectByNextDealSn(String ndsn);
}
