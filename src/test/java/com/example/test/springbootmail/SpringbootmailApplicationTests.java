package com.example.test.springbootmail;

import com.example.test.springbootmail.entity.Mail;
import com.example.test.springbootmail.service.IMailService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootmailApplicationTests {


    @Autowired
    private IMailService mailService;


    @Test
    public void sendSimpleMail(){
        Mail mail = Mail.builder().sendTo(new String[]{""})
                .html(false).subject("测试邮件［简单主题］").text("以下是文本内容").build();
        mailService.sendSimpleMail(mail);
    }

    @Test
    public void sendMail1(){
        Mail mail = Mail.builder().sendTo(new String[]{""})
                .subject("测试主题　无附件无html").text("文件内容　测试主题　无附件无html　").build();
        mailService.sendMail(mail);
    }
    @Test
    public void sendMail2(){
        Mail mail = Mail.builder().sendTo(new String[]{""})
                .subject("测试主题　无附件无html").text("文件内容　　无附件　无html 另启用别名").personalName("另启用别名").build();
        mailService.sendMail(mail);
    }
    @Test
    public void sendMail3(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html><head></head>")
                .append("<body><h1>测试邮件　无附件含html</h1><p>测试邮件　无附件含html 这是测试的文本内容</p></body>").append("</html>");
        Mail mail = Mail.builder().sendTo(new String[]{"", ""})
                .subject("测试主题　无附件html").html(Boolean.TRUE).text(stringBuilder.toString()).personalName("另启用别名").build();
        mailService.sendMail(mail);
    }
    @Test
    public void sendMail4(){
        String file = "/home/test.pdf";
        Mail mail = Mail.builder().sendTo(new String[]{""})
                .subject("测试主题　含附件无html").text("文件内容　　无附件　无html 另启用别名").filePath(file).personalName("另启用别名").build();
        mailService.sendMail(mail);
    }
    @Test
    public void sendMail5(){
        String file = "/home/test.pdf";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html><head></head>")
                .append("<body><h1>测试邮件　无附件含html</h1><p>测试邮件　含附件含html 这是测试的文本内容</p></body>").append("</html>");
        Mail mail = Mail.builder().sendTo(new String[]{""})
                .subject("测试主题　含附件含html").html(Boolean.TRUE).text(stringBuilder.toString()).filePath(file).personalName("另启用别名").build();
        mailService.sendMail(mail);
    }


}
