package com.example.myemail;

import java.util.Properties;


public class Email {
    // 发送邮件的服务器的IP和端口
    private static String mailServerHost;
    private static String mailServerPort;
    private static boolean isSSL;
    // 邮件发送者的地址
    private static String fromAddress;
    // 邮件接收者的地址
    private String toAddress;
    // 登陆邮件发送服务器的用户名和密码
    private static String userName;
    private static String password;
    // 是否需要身份验证
    private static boolean validate = false;
    // 邮件主题
    private String subject;
    // 邮件的文本内容
    private String content;
    // 邮件附件的文件名
    private String[] attachFileNames;

    static{
        mailServerHost = "smtpdm.aliyun.com";
        mailServerPort = "25";
        isSSL = false;
        validate = true;
        //这里需要改成你的信息
        userName = "libairui@jsgj123.top";
        password = "LiBairui007";
        fromAddress = "libairui@jsgj123.top";
    }

    /**
     * 获得邮件会话属性
     */
    public Properties getProperties(){
        Properties p = new Properties();
        p.put("mail.smtp.host", mailServerHost);
        p.put("mail.smtp.port", mailServerPort);
        p.put("mail.smtp.auth", validate ? "true" : "false");
        p.put("mail.stmp.timeout", "5000");
        return p;
    }

    public String getMailServerHost() {
        return mailServerHost;
    }
    public String getMailServerPort() {
        return mailServerPort;
    }
    public boolean isValidate() {
        return validate;
    }
    public String[] getAttachFileNames() {
        return attachFileNames;
    }
    public void setAttachFileNames(String[] fileNames) {
        this.attachFileNames = fileNames;
    }
    public String getFromAddress() {
        return fromAddress;
    }
    public String getPassword() {
        return password;
    }
    public String getToAddress() {
        return toAddress;
    }
    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }
    public String getUserName() {
        return userName;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String textContent) {
        this.content = textContent;
    }
    public static boolean getIsSSL() {
        return isSSL;
    }
    public static void setIsSSL(boolean isSSL) {
        Email.isSSL = isSSL;
    }
}
