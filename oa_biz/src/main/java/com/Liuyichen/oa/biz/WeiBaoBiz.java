package com.Liuyichen.oa.biz;

import com.Liuyichen.oa.entity.*;

import java.util.List;

public interface WeiBaoBiz {

    void save(WeiBao weiBao, List<WeiBaoItem> items);

    WeiBao get(int id);

    void delete(int id);

    List<WeiBaoItem> getItems(int cvid);

    List<WeiBaoDealRecord> getRecords(int cvid);

    List<WeiBao> getForSelf(String sn);

    List<WeiBao> getForDeal(String sn);

    void update(WeiBao weiBao, List<WeiBaoItem> items);

    void submit(int id);

    void deal(WeiBaoDealRecord weiBaoDealRecord);
}
