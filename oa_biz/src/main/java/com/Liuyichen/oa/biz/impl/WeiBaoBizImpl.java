package com.Liuyichen.oa.biz.impl;

import com.Liuyichen.oa.biz.WeiBaoBiz;
import com.Liuyichen.oa.dao.*;
import com.Liuyichen.oa.entity.*;
import com.Liuyichen.oa.global.Contant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("weiBaoBiz")
public class WeiBaoBizImpl implements WeiBaoBiz {
    @Qualifier("weiBaoDao")
    @Autowired
    private WeiBaoDao weiBaoDao;
    @Qualifier("weiBaoItemDao")
    @Autowired
    private WeiBaoItemDao weiBaoItemDao;
    @Qualifier("weiBaoDealRecordDao")
    @Autowired
    private WeiBaoDealRecordDao weiBaoDealRecordDao;
    @Qualifier("employeeDao")
    @Autowired
    private EmployeeDao employeeDao;
    @Qualifier("imageDao")
    @Autowired
    private ImageDao imageDao;


    public void save(WeiBao weiBao, List<WeiBaoItem> items) {
        weiBao.setCreateTime(new Date());
        weiBao.setNextDealSn(weiBao.getCreateSn());
        weiBao.setStatus(Contant.CLAIMVOUCHER_CREATED);
        weiBaoDao.insert(weiBao);

        for (WeiBaoItem item : items) {
            item.setClaimVoucherId(weiBao.getId());
            weiBaoItemDao.insert(item);
        }
    }

    public WeiBao get(int id) {
        return weiBaoDao.select(id);
    }

    public void delete(int id) {
        weiBaoDao.delete(id);
    }

    public List<WeiBaoItem> getItems(int cvid) {
        return weiBaoItemDao.selectByWeiBao(cvid);
    }

    public List<WeiBaoDealRecord> getRecords(int cvid) {
        return weiBaoDealRecordDao.selectByWeiBao(cvid);
    }

    public List<WeiBao> getForSelf(String sn) {
        return weiBaoDao.selectByCreateSn(sn);
    }

    public List<WeiBao> getForDeal(String sn) {
        return weiBaoDao.selectByNextDealSn(sn);
    }

    public void update(WeiBao weiBao, List<WeiBaoItem> items) {
        weiBao.setNextDealSn(weiBao.getCreateSn());
        weiBao.setStatus(Contant.CLAIMVOUCHER_CREATED);
        weiBaoDao.update(weiBao);

        List<WeiBaoItem> olds = weiBaoItemDao.selectByWeiBao(weiBao.getId());
        for (WeiBaoItem old : olds) {
            boolean isHave = false;
            for (WeiBaoItem item : items) {
                if (item.getId() == old.getId()) {
                    isHave = true;
                    break;
                }
            }
            if (!isHave) {
                weiBaoItemDao.delete(old.getId());
            }
        }
        for (WeiBaoItem item : items) {
            item.setClaimVoucherId(weiBao.getId());
            if (item.getId() > 0) {
                weiBaoItemDao.update(item);
            } else {
                weiBaoItemDao.insert(item);
            }
        }
    }

    public void submit(int id) {
        WeiBao weiBao = weiBaoDao.select(id);
        Employee employee = employeeDao.select(weiBao.getCreateSn());
        weiBao.setStatus(Contant.CLAIMVOUCHER_SUBMIT);
        if (employeeDao.selectByDepartmentAndPost(employee.getDepartmentSn(), Contant.POST_FM) != null) {
            weiBao.setNextDealSn(employeeDao.selectByDepartmentAndPost(employee.getDepartmentSn(), Contant.POST_FM).get(0).getSn());

        }

        weiBaoDao.update(weiBao);

        WeiBaoDealRecord weiBaoDealRecord = new WeiBaoDealRecord();
        weiBaoDealRecord.setDealWay(Contant.DEAL_SUBMIT);
        weiBaoDealRecord.setDealSn(employee.getSn());
        weiBaoDealRecord.setWeiBaoId(id);
        weiBaoDealRecord.setDealResult(Contant.CLAIMVOUCHER_SUBMIT);
        weiBaoDealRecord.setDealTime(new Date());
        weiBaoDealRecord.setComment("无");

        weiBaoDealRecordDao.insert(weiBaoDealRecord);
    }

    public void deal(WeiBaoDealRecord weiBaoDealRecord) {
        WeiBao weiBao = weiBaoDao.select(weiBaoDealRecord.getWeiBaoId());
        Employee employee = employeeDao.select(weiBaoDealRecord.getDealSn());
        if (weiBaoDealRecord.getDealWay().equals(Contant.DEAL_PASS)) {
            if (weiBao.getTotalAmount() <= Contant.LIMIT_CHECK && employee.getPost().equals(Contant.POST_CM)) {
                weiBao.setStatus(Contant.CLAIMVOUCHER_APPROVED);
                weiBao.setNextDealSn(employeeDao.selectByDepartmentAndPost(null, Contant.POST_GM).get(0).getSn());

                weiBaoDealRecord.setDealTime(new Date());
                weiBaoDealRecord.setDealResult(Contant.CLAIMVOUCHER_RECHECK);
            } else if (employee.getPost().equals(Contant.POST_GM)) {
                weiBao.setStatus(Contant.CLAIMVOUCHER_APPROVED);
                weiBao.setNextDealSn(employeeDao.selectByDepartmentAndPost(null, Contant.POST_CM2).get(0).getSn());

                weiBaoDealRecord.setDealTime(new Date());
                weiBaoDealRecord.setDealResult(Contant.CLAIMVOUCHER_RECHECK);
            } else if (employee.getPost().equals(Contant.POST_CM2)) {
                weiBao.setStatus(Contant.CLAIMVOUCHER_APPROVED);
                weiBao.setNextDealSn(employeeDao.selectByDepartmentAndPost(null, Contant.POST_SHENJI).get(0).getSn());
                weiBaoDealRecord.setDealTime(new Date());
                weiBaoDealRecord.setDealResult(Contant.CLAIMVOUCHER_RECHECK);
            } else if (employee.getPost().equals(Contant.POST_SHENJI)) {
                weiBao.setStatus(Contant.CLAIMVOUCHER_APPROVED);
                weiBao.setNextDealSn(employeeDao.selectByDepartmentAndPost(null, Contant.POST_SHENJI2).get(0).getSn());
                weiBaoDealRecord.setDealTime(new Date());
                weiBaoDealRecord.setDealResult(Contant.CLAIMVOUCHER_RECHECK);
            } else if (employee.getPost().equals(Contant.POST_SHENJI2)) {
                weiBao.setStatus(Contant.CLAIMVOUCHER_APPROVED);
                weiBao.setNextDealSn(employeeDao.selectByDepartmentAndPost(null, Contant.POST_CASHIER).get(0).getSn());
                weiBaoDealRecord.setDealTime(new Date());
                weiBaoDealRecord.setDealResult(Contant.CLAIMVOUCHER_RECHECK);
            } else {
                weiBao.setStatus(Contant.CLAIMVOUCHER_APPROVED);
                weiBao.setNextDealSn(employeeDao.selectByDepartmentAndPost(null, Contant.POST_CM).get(0).getSn());
                weiBaoDealRecord.setDealTime(new Date());
                weiBaoDealRecord.setDealResult(Contant.CLAIMVOUCHER_RECHECK);
            }
        } else if (weiBaoDealRecord.getDealWay().equals(Contant.DEAL_BACK)) {
            weiBao.setStatus(Contant.CLAIMVOUCHER_BACK);
            weiBao.setNextDealSn(weiBao.getCreateSn());
            weiBaoDealRecord.setDealTime(new Date());
            weiBaoDealRecord.setDealResult(Contant.CLAIMVOUCHER_BACK);

        } else if (weiBaoDealRecord.getDealWay().equals(Contant.DEAL_REJECT)) {
            weiBao.setStatus(Contant.CLAIMVOUCHER_TERMINATED);
            weiBao.setNextDealSn(null);

            weiBaoDealRecord.setDealTime(new Date());
            weiBaoDealRecord.setDealResult(Contant.CLAIMVOUCHER_TERMINATED);
        } else if (weiBaoDealRecord.getDealWay().equals(Contant.DEAL_PAID)) {
            weiBao.setStatus(Contant.CLAIMVOUCHER_PAID);
            weiBao.setNextDealSn(null);

            weiBaoDealRecord.setDealTime(new Date());
            weiBaoDealRecord.setDealResult(Contant.CLAIMVOUCHER_PAID);
        }
        weiBaoDao.update(weiBao);
        weiBaoDealRecordDao.insert(weiBaoDealRecord);
    }
}
