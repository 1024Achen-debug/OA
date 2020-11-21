package com.Liuyichen.oa.dao;

import com.Liuyichen.oa.entity.ClaimVoucherItem;
import com.Liuyichen.oa.entity.WeiBao;
import com.Liuyichen.oa.entity.WeiBaoItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("weiBaoItemDao")
public interface WeiBaoItemDao {
    void insert(WeiBaoItem weiBaoItem);

    void update(WeiBaoItem weiBaoItem);

    void delete(int id);

    List<WeiBaoItem> selectByWeiBao(int cvid);
}
