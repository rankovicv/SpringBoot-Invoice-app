package com.code.example.controllers;

import com.code.example.persistence.entities.User;
import com.code.example.security.CurrentUser;
import com.code.example.services.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by veljko
 */
@Slf4j
@Controller
@RequestMapping(path = "admin")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {

    private final @NonNull
    UserService userService;

    @GetMapping()
    public String getAdminPage(Model model) {

        CurrentUser authUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<User> users = userService.getAllUsers();
        //remove logged user;

        model.addAttribute("users", users);
        model.addAttribute("username", authUser.getUsername());

        return "admin/admin";
    }


    @ResponseBody
    @PostMapping(value = "/changeRole/{id}")
    public ResponseEntity<String> changeUserRole(@PathVariable String id, @RequestParam(value = "role") String role) {

        userService.changeUserRole(Long.parseLong(id), Long.parseLong(role));

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {

        userService.deleteUser(Long.parseLong(id));

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
