package com.mq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mq.po.Employee;

/**
 * @author: JunLog
 * @Description: *
 * Date: 2022/7/10 11:08
 */
public interface EmployeeService extends IService<Employee> {

    /**
     * 新增员工
     * @param employee
     * @return
     */
    boolean InsertEmployee(Employee employee);

}
