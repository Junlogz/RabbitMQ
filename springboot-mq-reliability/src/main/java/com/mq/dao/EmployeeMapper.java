package com.mq.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mq.po.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

/**
 * @author: JunLog
 * @Description: *
 * Date: 2022/7/10 11:07
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {


}
