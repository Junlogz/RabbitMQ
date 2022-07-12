package com.mq.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mq.po.MailSendLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
 * @author: JunLog
 * @Description: *
 * Date: 2022/7/10 11:07
 */
@Mapper
public interface MailSendLogMapper extends BaseMapper<MailSendLog> {

    @Update("update mail_send_log set count=count+1,update_time=#{date} where msg_id=#{msgId}")
    void updateCount(@Param("msgId")String msgId, @Param("date")Date date);

    @Select("select * from mail_send_log where status=0 and try_time < now()")
    List<MailSendLog> getMailSendLogsByStatus();
}
