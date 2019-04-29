package com.code.example.registration.listener;

import com.code.example.mail.EmailService;
import com.code.example.persistence.entities.User;
import com.code.example.registration.OnRegistrationCompleteEvent;
import com.code.example.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Created by veljko on 16.9.18.
 */
@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final @NotNull
    EmailService emailService;

    private final @NotNull
    UserService userService;

    @Autowired
    MessageSource messages;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) {
        final User user = event.getUser();
        final String token = UUID.randomUUID().toString();
        userService.createVerificationTokenForUser(user, token);

        constructEmailMessage(event, user, token);
    }

    private final void constructEmailMessage(final OnRegistrationCompleteEvent event, final User user, final String token) {
        final String recipientAddress = user.getUsername();
        final String subject = "Registration Confirmation";
//        final String confirmationUrl = event.getAppUrl() + "/templates/registrationConfirm" + token;
        final String confirmationUrl = "http://localhost:8080/registrationConfirm?token=" + token;
        final String message = messages.getMessage("message.regSucc", null, event.getLocale());

        emailService.sendSimpleMessage(recipientAddress, subject, message + " \r\n" + confirmationUrl);
    }
}
