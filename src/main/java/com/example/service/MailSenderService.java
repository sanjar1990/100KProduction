package com.example.service;

import com.example.entity.EmailHistoryEntity;
import com.example.util.JWTUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class MailSenderService {
    private JavaMailSender javaMailSender;
    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    private EmailHistoryService emailHistoryService;
    @Autowired
    public void setEmailHistoryService(EmailHistoryService emailHistoryService) {
        this.emailHistoryService = emailHistoryService;
    }

    @Value("${server.url}")
    private String serverUrl;

    @Value("${spring.mail.username}")
    private String fromEmail;

//    void sendMimeEmail(String toAccount, String text) {
//        try {
//            MimeMessage msg = javaMailSender.createMimeMessage();
//            msg.setFrom(fromEmail);
//            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
//            helper.setTo(toAccount);
//            helper.setSubject("Kun uz registration compilation");
//            helper.setText(text, true);
//            javaMailSender.send(msg);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void sendMimeEmail(String toAccount, String text){
        ExecutorService emailExecutor = Executors.newSingleThreadExecutor();
        emailExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    MimeMessage msg=javaMailSender.createMimeMessage();
                    MimeMessageHelper helper=new MimeMessageHelper(msg,true);
                    helper.setTo(toAccount);
                    helper.setSubject("100k.uz");
                    helper.setText("Xush kelibsiz",text);
                    javaMailSender.send(msg);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        emailExecutor.shutdown();
    }

    public void sendEmailVerification(String toAccount, Integer code) {
        EmailHistoryEntity emailHistoryEntity = new EmailHistoryEntity();
        emailHistoryEntity.setEmail(toAccount);
        emailHistoryService.create(emailHistoryEntity);
        String builder = "<h1 style=\"text-align: center\">Hello %s</h1>" +
                "<p>" +
                " Bu 100k.uz saytiga kirish uchun kod" +
                "</p>" +
                String.format("<h1 style=\"text-align: center\">%s</h1>", code);

        sendMimeEmail(toAccount, builder);
    }
}
