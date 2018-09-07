package com.code.example.mail;

import org.springframework.mail.SimpleMailMessage;

/**
 * Created by veljko on 7.9.18.
 */
public interface EmailService {

    void sendSimpleMessage(String to,
                           String subject,
                           String text);
    void sendSimpleMessageUsingTemplate(String to,
                                        String subject,
                                        SimpleMailMessage template,
                                        String ...templateArgs);
    void sendMessageWithAttachment(String to,
                                   String subject,
                                   String text,
                                   String pathToAttachment);
}
