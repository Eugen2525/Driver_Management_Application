package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by Eugen2525 on 12/13/2015.
 */
@Service
public class SendEmailService {
    @Autowired
    private MailSender mailSender; // MailSender interface defines a strategy
    // for sending simple mails
    @Async
    public void sendEmail(String toAddress, String fromAddress, String subject, String msgBody) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom(fromAddress);
        smm.setTo(toAddress);
        smm.setSubject(subject);
        smm.setText(msgBody);
        mailSender.send(smm);
    }
}
