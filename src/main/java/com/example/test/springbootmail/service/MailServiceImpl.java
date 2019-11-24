package com.example.test.springbootmail.service;

import com.example.test.springbootmail.entity.Mail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * @Description: Developed for springbootmail
 * @Project: springbootmail
 * @Package: com.example.test.springbootmail.service
 * @ClassName: MailServiceImpl
 * @Date: Create in 下午6:14 19-11-24
 */
@Service
@Slf4j
public class MailServiceImpl implements  IMailService {

    @Autowired
    private JavaMailSenderImpl mailSender;


    //发送邮箱地址
    @Value("${spring.mail.from}")
    private String defaultFrom;

    //发送者名称
    @Value("${spring.mail.fromname}")
    private String defaultFromName;

    @Override
    public void sendSimpleMail(Mail mail) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(mail.getSendTo());
        simpleMailMessage.setFrom(defaultFrom);
        simpleMailMessage.setSubject(mail.getSubject());
        simpleMailMessage.setText(mail.getText());
        mailSender.send(simpleMailMessage);
        log.info("邮件发送成功文本");
    }


    @Override
    public void sendMail(Mail mail) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        String fromName = mail.getPersonalName()==null?defaultFromName:mail.getPersonalName();
        try {
            if(mail.getFilePath()!=null){
                //包含附件
                mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            }else{
                mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            }
            mimeMessageHelper.setTo(mail.getSendTo());
            if(fromName==null){
                mimeMessageHelper.setFrom(defaultFrom);
            }else{
                mimeMessageHelper.setFrom(defaultFrom,fromName);
            }
            mimeMessageHelper.setSubject(mail.getSubject());
            //是否为html
            if(mail.isHtml()){
                mimeMessageHelper.setText(mail.getText(),true);
            }else{
                mimeMessageHelper.setText(mail.getText());
            }
            //附件
            if(mail.getFilePath()!=null){
                File file = new File(mail.getFilePath());
                String fileName = mail.getFilePath().substring(mail.getFilePath().lastIndexOf(File.separator)+1);
                mimeMessageHelper.addAttachment(fileName,file);
            }
            mailSender.send(mimeMessage);
            log.info("邮件发送成功");
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error("邮件发送异常",e);
        }

    }



}
