package com.Liuyichen.oa.dao;

import com.Liuyichen.oa.entity.DealRecord;
import com.Liuyichen.oa.entity.WeiBaoDealRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("weiBaoDealRecordDao")
public interface WeiBaoDealRecordDao {
    void insert(WeiBaoDealRecord weiBaoDealRecord);

    List<WeiBaoDealRecord> selectByWeiBao(int cvid);
}
