package com.mq.controller;

import com.mq.po.Employee;
import com.mq.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: JunLog
 * @Description: *
 * Date: 2022/7/10 14:30
 */
@RestController
@RequestMapping("emp")
public class EmployeeController {

    @Autowired
    protected EmployeeServiceImpl employeeService;

    @PostMapping("/add")
    public ResponseEntity<String> addEmp(Employee employee){
        boolean b = employeeService.InsertEmployee(employee);
        if (b) {
            return ResponseEntity.ok("添加成功");
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
