package com.sky.service;

import com.github.pagehelper.Page;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.entity.Employee;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    Page<Employee> findPage(Integer page, Integer pageSize,String name);

    void saveBatch(Employee employee);

    Employee showById(Long id);

    void update(Employee employee);

    void updateStatus(Integer status, Integer id);
}
