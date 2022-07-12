package com.mq.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("employee")
public class Employee implements Serializable {
    private Integer id;

    private String name;

    private String gender;
}