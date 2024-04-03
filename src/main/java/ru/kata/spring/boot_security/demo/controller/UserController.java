package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
public class UserController {

    private final UserService us;

    @Autowired
    public UserController(UserService userService) {
        this.us = userService;
    }

    @GetMapping("/")
    public String getListUsers(Model model) {
        model.addAttribute("listUsers", us.getListUsers());
        return "users";
    }

    @GetMapping("/new")
    public String getCreateUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping("/addUser")
    public String createUser(@ModelAttribute("user") User user) {
            us.addUser(user);
            return "redirect:/";
    }


    @GetMapping("/update")
    public String getUpdateUser(@RequestParam("id") Long id,
                                Model model) {
        User user = us.getUser(id);
        model.addAttribute("user", user);
            return "update";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
            us.updateUser(user);
            return "redirect:/";
    }


    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
            us.deleteUser(id);
            return "redirect:/";
    }
    }
