package com.code.example.controllers;

import com.code.example.commands.UserCommand;
import com.code.example.persistence.entities.User;
import com.code.example.persistence.entities.UserCompany;
import com.code.example.security.CurrentUser;
import com.code.example.services.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
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

        UserCompany savedCompany = userService.saveCompany(userCompany);

        model.addAttribute("company", savedCompany);
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

    @PostMapping("/edit")
    public ResponseEntity<String> saveUserData(@Valid @RequestBody UserCommand userCommand) {

        CurrentUser myUserDetails = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userCommand.setId(myUserDetails.getUserId());

        userService.saveUserCommand(userCommand);

        myUserDetails.setName(userCommand.getName());
        myUserDetails.setLastName(userCommand.getLastName());
        CurrentUser.updateUser(myUserDetails);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping("/edit")
    public String getUserEdit(Model model) {

        CurrentUser myUserDetails = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserCommand userCommand = userService.findUserById(myUserDetails.getUserId());

        model.addAttribute("user", userCommand);

        return "user/userform";
    }

    @ResponseBody
    @GetMapping("/checkPassword")
    public boolean getPasswordHash(@RequestParam(value = "oldPass") String pass) {

        CurrentUser myUserDetails = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userService.checkPassword(myUserDetails.getUserId(), pass);
    }

    @ResponseBody
    @PostMapping(value = "/changePassword", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> changeUserPassword(@RequestBody String pass) {

        CurrentUser myUserDetails = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

       userService.changeUserPassword(pass, myUserDetails.getUserId());

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
