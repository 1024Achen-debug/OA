package com.Liuyichen.oa.global;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

public class Contant {
    //职务
    public static final String POST_STAFF = "警员";
    public static final String POST_ADMIN = "admin";
    public static final String POST_GM = "装备科审核人";
    public static final String POST_FM = "申请单位负责人";
    public static final String POST_CM = "派出所主管";
    public static final String POST_SHENJI = "审计部审核人";
    public static final String POST_CM2 = "装备科负责人";
    public static final String POST_SHENJI2 = "审计部负责人";
    public static final String POST_CASHIER = "财务审核人";

    //用处
    public static final String traffic = "办公耗材";
    public static final String catering = "电脑";
    public static final String Accommodation = "打印机";
    public static final String work = "家具";
    public static final String qt = "其他";


    public static List<String> getPost() {
        List<String> list = new ArrayList<String>();
        list.add(POST_GM);
        list.add(POST_CASHIER);
        list.add(POST_FM);
        list.add(POST_STAFF);
        list.add(POST_ADMIN);
        list.add(POST_CM);
        list.add(POST_CM2);
        list.add(POST_SHENJI);
        list.add(POST_SHENJI2);
        return list;
    }

    //费用类别
    public static List<String> getItems() {
        List<String> list = new ArrayList<String>();
        list.add(traffic);
        list.add(catering);
        list.add(Accommodation);
        list.add(work);
        list.add(qt);
        return list;
    }

    //报销单状态
    public static final String CLAIMVOUCHER_CREATED = "新创建";
    public static final String CLAIMVOUCHER_SUBMIT = "已提交";
    public static final String CLAIMVOUCHER_APPROVED = "已审核";
    public static final String CLAIMVOUCHER_BACK = "已打回";
    public static final String CLAIMVOUCHER_TERMINATED = "已终止";
    public static final String CLAIMVOUCHER_RECHECK = "待复审";
    public static final String CLAIMVOUCHER_PAID = "已打款";

    //审核额度
    public static final double LIMIT_CHECK = 1500000000000.00;

    //处理方式
    public static final String DEAL_CREATE = "创建";
    public static final String DEAL_SUBMIT = "提交";
    public static final String DEAL_UPDATE = "修改";
    public static final String DEAL_BACK = "打回";
    public static final String DEAL_REJECT = "拒绝";
    public static final String DEAL_PASS = "通过";
    public static final String DEAL_PAID = "打款";
}
