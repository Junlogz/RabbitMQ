package com.mq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mq.po.MailSendLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author: JunLog
 * @Description: *
 * Date: 2022/7/10 11:08
 */
public interface MailSendLogService extends IService<MailSendLog> {

    /**
     * 发送消息成功的话修改status为1也就是发送成功
     * @param msgId
     * @param status
     * @return
     */
    public boolean updateMailSendLogStatus(@Param("msgId") String msgId, @Param("status") Integer status);

    /**
     * 定时任务中查询所有status为0也就是发送中，进行后续重试
     * @return
     */
    List<MailSendLog> getMailSendLogsByStatus();

    /**
     * 定时重试count次数+1
     * @param msgId
     * @param date
     */
    void updateCount(String msgId, Date date);
}
