package com.mq.task;

import com.mq.constant.MailConstants;
import com.mq.po.Employee;
import com.mq.po.MailSendLog;
import com.mq.service.EmployeeService;
import com.mq.service.MailSendLogService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class MailSendTask {
    @Autowired
    MailSendLogService mailSendLogService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    EmployeeService employeeService;

    @Scheduled(cron = "0/10 * * * * ?")
    public void mailResendTask() {
        List<MailSendLog> logs = mailSendLogService.getMailSendLogsByStatus();
        if (logs == null || logs.size() == 0) {
            return;
        }
        for (MailSendLog log : logs) {
            if (log.getCount() >= 3) {
                mailSendLogService.updateMailSendLogStatus(log.getMsgId(), 2);//直接设置该条消息发送失败
            }else{
                mailSendLogService.updateCount(log.getMsgId(), new Date());
                Employee emp = employeeService.getById(log.getEmpId());
                rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_ROUTING_KEY_NAME, emp, new CorrelationData(log.getMsgId()));
            }
        }

//        logs.forEach(mailSendLog->{
//            if (mailSendLog.getCount() >= 3) {
//                mailSendLogService.updateMailSendLogStatus(mailSendLog.getMsgId(), 2);//直接设置该条消息发送失败
//            }else{
//                mailSendLogService.updateCount(mailSendLog.getMsgId(), new Date());
//                Employee emp = employeeService.getById(mailSendLog.getEmpId());
//                rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_ROUTING_KEY_NAME, emp, new CorrelationData(mailSendLog.getMsgId()));
//            }
//        });
    }

}
