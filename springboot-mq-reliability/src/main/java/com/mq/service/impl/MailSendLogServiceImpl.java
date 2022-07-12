package com.mq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mq.dao.MailSendLogMapper;
import com.mq.po.MailSendLog;
import com.mq.service.MailSendLogService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: JunLog
 * @Description: *
 * Date: 2022/7/10 11:09
 */
@Service
public class MailSendLogServiceImpl extends ServiceImpl<MailSendLogMapper, MailSendLog> implements MailSendLogService {

    @Override
    public boolean updateMailSendLogStatus(String msgId, Integer status) {
        LambdaUpdateWrapper<MailSendLog> updateWrapper = new LambdaUpdateWrapper<>();

        updateWrapper.eq(MailSendLog::getMsgId,msgId)
                .set(MailSendLog::getStatus,status);

        return this.update(updateWrapper);
    }

    @Override
    public List<MailSendLog> getMailSendLogsByStatus() {
        LambdaQueryWrapper<MailSendLog> queryWrapper= new LambdaQueryWrapper<>();

        queryWrapper.eq(MailSendLog::getStatus,0)
                .le(MailSendLog::getTryTime,System.currentTimeMillis());

        List<MailSendLog> list = this.list(queryWrapper);
        return list;
    }

    @Override
    public void updateCount(String msgId, Date date) {
        this.baseMapper.updateCount(msgId,date);
    }

}
