package com.example.test.springbootmail.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @Description: Developed for springbootmail
 * @Project: springbootmail
 * @Package: com.example.test.springbootmail.entity
 * @ClassName: Mail
 * @Date: Create in 下午5:51 19-11-24
 */
@Data
@Builder
public class Mail {


    /**
     * 发送者名字
     */
    private String personalName;

    /**
     * 目标邮箱　可以为多个
     */
    private String[] sendTo;

    /**
     * 主题
     */
    private String subject;

    /**
     * 具体内容
     */
    private String text;

    /**
     * 是否为html
     */
    private boolean html;

    /**
     * 附件信息
     */
    private String filePath;
}
