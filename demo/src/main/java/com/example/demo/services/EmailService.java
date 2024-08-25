package com.example.demo.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MailBody;

@Service
public class EmailService {
   private final JavaMailSender javaMailSender;

   public EmailService(JavaMailSender javaMailSender) {
       this.javaMailSender = javaMailSender;
   }

   public void sendEmail(MailBody mailBody) {
       SimpleMailMessage mailMessage = new SimpleMailMessage();
       mailMessage.setTo(mailBody.to());
       mailMessage.setSubject(mailBody.subject());
       mailMessage.setText(mailBody.body());
       javaMailSender.send(mailMessage);
   }
}
