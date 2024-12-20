package org.example.movieweb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

  private final JavaMailSender emailSender;

  public void sendMailRegistration(String receiver , String subject, String content) {
    // Create a Simple MailMessage.
    SimpleMailMessage message = new SimpleMailMessage();

    message.setTo(receiver);
    message.setSubject(subject);
    message.setText(content);

    emailSender.send(message);
  }
}
