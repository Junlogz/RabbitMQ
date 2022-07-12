package com.mq.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mq.constant.MailConstants;
import com.mq.dao.EmployeeMapper;
import com.mq.dao.MailSendLogMapper;
import com.mq.po.Employee;
import com.mq.po.MailSendLog;
import com.mq.service.EmployeeService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @author: JunLog
 * @Description: *
 * Date: 2022/7/10 11:09
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private MailSendLogMapper mailSendLogMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public boolean InsertEmployee(Employee employee) {
        boolean save = this.save(employee);
        if(save) {
            Employee emp = employeeMapper.selectById(employee.getId());
            //生成消息的唯一id
            String msgId = IdUtil.simpleUUID();
            MailSendLog mailSendLog = new MailSendLog();
            mailSendLog.setMsgId(msgId);
            mailSendLog.setCreateTime(new Date());
            mailSendLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
            mailSendLog.setRouteKey(MailConstants.MAIL_ROUTING_KEY_NAME);
            mailSendLog.setEmpId(emp.getId());
            mailSendLog.setCount(0);
            mailSendLog.setTryTime(new Date(System.currentTimeMillis() + 1000 * 60 * MailConstants.MSG_TIMEOUT));
            mailSendLogMapper.insert(mailSendLog);
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_ROUTING_KEY_NAME, emp, new CorrelationData(msgId));
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_ROUTING_KEY_NAME, emp, new CorrelationData(msgId));
        }
        return save;
    }
}
