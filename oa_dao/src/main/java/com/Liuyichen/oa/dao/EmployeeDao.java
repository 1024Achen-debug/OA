package com.Liuyichen.oa.dao;

import com.Liuyichen.oa.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("employeeDao")
public interface EmployeeDao {
    void insert(Employee department);

    void update(Employee department);


    void delete(String sn);

    Employee select(String sn);

    List<Employee> selectStatus();

    List<Employee> selectAll();

    List<Employee> selectByDepartmentAndPost(@Param("dsn") String dsn, @Param("post") String post);
}
