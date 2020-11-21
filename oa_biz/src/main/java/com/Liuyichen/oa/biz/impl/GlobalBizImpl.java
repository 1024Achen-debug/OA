package com.Liuyichen.oa.biz.impl;

import com.Liuyichen.oa.biz.GlobalBiz;
import com.Liuyichen.oa.dao.EmployeeDao;
import com.Liuyichen.oa.entity.Employee;
import com.Liuyichen.oa.global.Contant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("globalBiz")
public class GlobalBizImpl implements GlobalBiz {
    @Qualifier("employeeDao")
    @Autowired
    private EmployeeDao employeeDao;

    public Employee login(String sn, String password) {
        Employee employee = employeeDao.select(sn);
        if (employee != null && employee.getPassword().equals(password)) {
            return employee;
        } else if (employee.getLogin_num() == 3) {
            employee.setDepartmentSn(employeeDao.select(sn).getDepartmentSn());
            employee.setCloack_status("true");
            employee.setClocktime(new Date());
            employee.setLogin_num(0);
            Date date = employee.getClocktime();
            Date aftertime = new Date(date.getTime() + 30 * 60 * 1000);
            employee.setClock_open_time(aftertime);
            employeeDao.update(employee);
        } else {
            employee.setDepartmentSn(employeeDao.select(sn).getDepartmentSn());
            int num = employee.getLogin_num() + 1;
            employee.setLogin_num(num);
            employeeDao.update(employee);
        }
        return null;
    }

    public void changePassword(Employee employee) {
        employeeDao.update(employee);
    }
}
