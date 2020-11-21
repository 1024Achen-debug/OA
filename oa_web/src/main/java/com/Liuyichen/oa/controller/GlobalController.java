package com.Liuyichen.oa.controller;

import com.Liuyichen.oa.biz.GlobalBiz;
import com.Liuyichen.oa.dao.EmployeeDao;
import com.Liuyichen.oa.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;

@Controller("globalController")
public class GlobalController {
    @Autowired
    private GlobalBiz globalBiz;
    @Qualifier("employeeDao")
    @Autowired
    private EmployeeDao employeeDao;


    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/login")
    public String loging(HttpSession session, @RequestParam String sn, @RequestParam String password) throws ParseException {
        Employee employee = globalBiz.login(sn, password);
        Date date = new Date();
        if (employee == null || employee.getCloack_status().equals("true")) {
            if (date.getTime() >= employee.getClock_open_time().getTime()) {
                employee.setDepartmentSn(employeeDao.select(sn).getDepartmentSn());
                employee.setLogin_num(0);
                employee.setCloack_status("false");
                employeeDao.update(employee);
                session.setAttribute("employee", employee);
                return "redirect:self";
            }
            return "redirect:to_login";
        }
        employee.setLogin_num(0);
        employeeDao.update(employee);
        session.setAttribute("employee", employee);
        return "redirect:self";
    }

    @RequestMapping("/self")
    public String self() {
        return "self";

    }

    @RequestMapping("/quit")
    public String quit(HttpSession session) {
        session.setAttribute("employee", null);
        return "redirect:to_login";
    }

    @RequestMapping("/to_change_password")
    public String toChangePassword() {
        return "change_password";
    }

    @RequestMapping("/change_password")
    public String changePassword(HttpSession session, @RequestParam String old, @RequestParam String new1, @RequestParam String new2) {
        Employee employee = (Employee) session.getAttribute("employee");

        if (employee.getPassword().equals(old)) {
            if (new1.equals(new2)) {
                employee.setPassword(new2);
                globalBiz.changePassword(employee);
            }
            return "redirect:self";
        }
        return "redirect:to_change_password";
    }
}
