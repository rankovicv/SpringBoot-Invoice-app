package com.code.example.controllers;

import com.code.example.mail.EmailService;
import com.code.example.persistence.entities.User;
import com.code.example.persistence.entities.VerificationToken;
import com.code.example.registration.OnRegistrationCompleteEvent;
import com.code.example.services.UserService;
import com.code.example.util.GenericResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by veljko on 9.9.18.
 */
@Controller
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginController {

    private final @NonNull
    UserService userService;

    private final @NonNull
    EmailService emailService;

    private final @NonNull
    ApplicationEventPublisher eventPublisher;

    private final @NonNull
    MessageSource messages;

    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public String getLoginForm() {
        log.debug("Getting Login page");

        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        User userDto = new User();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getUsername());
        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.username",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            User registered = userService.saveUser(user);

            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                    (registered, request.getLocale(), appUrl));

            modelAndView.addObject("successMessage", "User has been registered successfully, we sent you an confirmation email");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
    public String confirmRegistration(final HttpServletRequest request, final Model model, @RequestParam("token") final String token) throws UnsupportedEncodingException {
        Locale locale = request.getLocale();
        final String result = userService.validateVerificationToken(token);
        if (result.equals("valid")) {
            model.addAttribute("message", messages.getMessage("message.accountVerified", null, locale));
            return "registrationConfirm";
        }

        model.addAttribute("message", messages.getMessage("auth.message." + result, null, locale));
        model.addAttribute("expired", "expired".equals(result));
        model.addAttribute("token", token);
        return "badUser";
    }

    @RequestMapping(value = "/user/resetRegistrationToken", method = RequestMethod.GET)
    @ResponseBody
    public GenericResponse resetRegistrationToken(final HttpServletRequest request, @RequestParam("token") final String existingToken) {
        final VerificationToken newToken = userService.generateNewVerificationToken(existingToken);
        final User user = userService.getUserByVerificationToken(newToken.getToken());

        constructEmailMessage(request.getLocale(), user, newToken.getToken());

        return new GenericResponse(messages.getMessage("message.resendToken", null, request.getLocale()));
    }

    @RequestMapping(value = "/user/resendRegistrationToken", method = RequestMethod.GET)
    @ResponseBody
    public GenericResponse resendRegistrationToken(final HttpServletRequest request, @RequestParam("email") final String email) {
        final User user = userService.findUserByEmail(email);

        final String token = UUID.randomUUID().toString();
        userService.createVerificationTokenForUser(user, token);

        constructEmailMessage(request.getLocale(), user, token);

        return new GenericResponse(messages.getMessage("message.resendToken", null, request.getLocale()));
    }

    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public String returnDeniedPage() {
        return "access-denied";
    }

    private final void constructEmailMessage(final Locale locale, final User user, final String token) {
        final String recipientAddress = user.getUsername();
        final String subject = "Resend Registration Token";
        final String confirmationUrl = "http://localhost:8080/registrationConfirm?token=" + token;
        final String message = messages.getMessage("message.resendToken", null, locale);

        emailService.sendSimpleMessage(recipientAddress, subject, message + " \r\n" + confirmationUrl);
    }

}

