package com.Liuyichen.oa.controller;


import com.Liuyichen.oa.biz.DepartmentBiz;
import com.Liuyichen.oa.biz.EmployeeBiz;
import com.Liuyichen.oa.entity.Employee;
import com.Liuyichen.oa.global.Contant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;

@Controller("employeeController")
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private DepartmentBiz departmentBiz;
    @Autowired
    private EmployeeBiz employeeBiz;

    @RequestMapping("/list")
    public String list(Map<String, Object> map) {
        map.put("list", employeeBiz.getAll());
        return "employee_list";
    }

    @RequestMapping(value = "/to_bean", params = "sn")
    public String toBaen(String sn) {
        Employee employee = employeeBiz.get(sn);
        Date date = new Date();
        employee.setCloack_status("false");
        employee.setClock_open_time(date);
        employeeBiz.edit(employee);
        return "redirect:bean";
    }

    @RequestMapping("/bean")
    public String Baen(Map<String, Object> map) {
        map.put("list", employeeBiz.getStatus());
        return "employee_ben_list";
    }

    @RequestMapping("/to_add")
    public String toAdd(Map<String, Object> map) {
        map.put("employee", new Employee());
        map.put("dlist", departmentBiz.getAll());
        map.put("plist", Contant.getPost());
        return "employee_add";
    }

    @RequestMapping("/add")
    public String add(Employee employee) {
        employeeBiz.add(employee);
        return "redirect:list";
    }

    @RequestMapping(value = "/to_update", params = "sn")
    public String toUpDate(String sn, Map<String, Object> map) {
        map.put("dlist", departmentBiz.getAll());
        map.put("plist", Contant.getPost());
        map.put("employee", employeeBiz.get(sn));
        return "employee_update";
    }

    @RequestMapping("/update")
    public String upDate(Employee employee) {
        employeeBiz.edit(employee);
        return "redirect:list";
    }

    @RequestMapping(value = "/remove", params = "sn")
    public String toUpDate(String sn) {
        employeeBiz.remove(sn);
        return "redirect:list";
    }

}
