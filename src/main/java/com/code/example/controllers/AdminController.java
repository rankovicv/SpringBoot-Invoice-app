package com.code.example.controllers;

import com.code.example.persistence.entities.User;
import com.code.example.security.CurrentUser;
import com.code.example.services.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

/**
 * Created by veljko
 */
@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {

    private final @NonNull
    UserService userService;

    @GetMapping("/admin")
    public String getAdminPage(Model model) {

        CurrentUser authUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<User> users = userService.getAllUsers();
        //remove logged user;

        model.addAttribute("users", users);
        model.addAttribute("username", authUser.getUsername());

        return "admin/admin";
    }

}
