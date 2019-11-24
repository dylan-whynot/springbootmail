package com.example.test.springbootmail.service;

import com.example.test.springbootmail.entity.Mail;


/**
 * @program: springbootmail
 * @description: 发送邮件接口
 * @author: wu haiyang
 * @create: 2019-11-24 18:13
 **/
public interface IMailService {

    /**
     * 最基本的文本邮件　不支持别名和附件　html
     * @param mail
     */
    void sendSimpleMail(Mail mail);

    /**
     * 基本的附件
     * @param mail
     */
    void sendMail(Mail mail);

}
