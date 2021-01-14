package com.yorijori.cook.handler;

import java.io.UnsupportedEncodingException;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailHandler {
	
	private JavaMailSender mailSender;
    private MimeMessage message;
    private MimeMessageHelper messageHelper;
    
    
    public MailHandler(JavaMailSender mailSender) throws MessagingException {
        this.mailSender = mailSender;
        message = this.mailSender.createMimeMessage();
        messageHelper = new MimeMessageHelper(message, true, "UTF-8");
    }
 
// 	----------------By ����----------------�̸��� Ÿ��Ʋ
    public void setSubject(String subject) throws MessagingException {
        messageHelper.setSubject(subject);
        
    }
// 	----------------By ����----------------�̸��� TEXT
    public void setText(String htmlContent) throws MessagingException {
        messageHelper.setText(htmlContent, true);
        
    }
// 	----------------By ����----------------������ ��� �̸���
    public void setFrom(String email, String name) throws UnsupportedEncodingException, MessagingException {
        messageHelper.setFrom(email, name);
    }
    
// 	----------------By ����----------------�޴� ��� �̸��� 
    public void setTo(String email) throws MessagingException {
        messageHelper.setTo(email);
    }
    
    public void addInline(String contentId, DataSource dataSource) throws MessagingException {
        messageHelper.addInline(contentId, dataSource);
    }
    
    public void send() {
        try {
            mailSender.send(message);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
