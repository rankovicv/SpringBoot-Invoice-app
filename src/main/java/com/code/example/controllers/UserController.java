package com.code.example.controllers;

import com.code.example.persistence.entities.User;
import com.code.example.persistence.entities.UserCompany;
import com.code.example.security.CurrentUser;
import com.code.example.services.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Created by veljko on 19.9.18.
 */
@Slf4j
@Controller
@RequestMapping(path = "/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final @NonNull
    UserService userService;

//    @GetMapping("/company")
//    public String getUserCompany() {
//        return "t";
//    }

    @PostMapping("/company")
    public String saveUserCompany(@Valid UserCompany userCompany, BindingResult bindingResult, Model model) {

        log.debug("Save user company");

        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = new User();
        user.setId(currentUser.getUserId());
        userCompany.setUser(user);
        if(bindingResult.hasErrors()){

            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));
            return "company/companyform";
        }

        userService.saveCompany(userCompany);

        model.addAttribute("company", userCompany);
        model.addAttribute("successMessage", "Company data saved!");

        return "company/companyform";
    }

    @GetMapping("/company/edit")
    public String getUserCompanyAddPage(Model model) {

        CurrentUser myUserDetails = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserCompany userCompany = userService.getUserCompany(myUserDetails.getUserId());

        if(userCompany == null) {
            model.addAttribute("userCompany", new UserCompany());
        } else {
            model.addAttribute("userCompany", userCompany);
        }

        model.addAttribute("successMessage", "");

        return "company/companyform";
    }
}
