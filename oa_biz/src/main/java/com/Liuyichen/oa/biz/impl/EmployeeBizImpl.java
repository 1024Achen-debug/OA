package com.Liuyichen.oa.biz.impl;

import com.Liuyichen.oa.biz.EmployeeBiz;
import com.Liuyichen.oa.dao.EmployeeDao;
import com.Liuyichen.oa.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeBiz")
public class EmployeeBizImpl implements EmployeeBiz {
    @Qualifier("employeeDao")
    @Autowired
    private EmployeeDao employeeDao;

    public void add(Employee employee) {
        employee.setPassword("000000");
        employee.setCloack_status("false");
        employeeDao.insert(employee);
    }

    public void edit(Employee employee) {
        employeeDao.update(employee);
    }

    public void remove(String sn) {
        employeeDao.delete(sn);
    }

    public Employee get(String sn) {
        return employeeDao.select(sn);
    }

    public List<Employee> getStatus() {
        return employeeDao.selectStatus();
    }

    public List<Employee> getAll() {
        return employeeDao.selectAll();
    }
}
