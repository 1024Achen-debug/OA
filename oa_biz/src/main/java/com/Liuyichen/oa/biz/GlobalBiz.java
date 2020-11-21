package com.Liuyichen.oa.biz;

import com.Liuyichen.oa.entity.Employee;

public interface GlobalBiz {

    Employee login(String sn, String password);

    void changePassword(Employee employee);
}
