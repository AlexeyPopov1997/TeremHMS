package org.example.TeremHMS.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.example.TeremHMS.domain.Role;
import org.example.TeremHMS.domain.User;
import org.example.TeremHMS.service.UserService;

import java.util.Map;
import java.util.List;
import java.util.Collections;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping
    public String showUserList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userPage";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping("{user}")
    public String editUser(@PathVariable Long user, Model model){
        List<User> users = userService.findAllById(Collections.singleton(user));
        User user1 = users.get(0);
        model.addAttribute("user", user1);
        model.addAttribute("roles", Role.values());
        return "userEditPage";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @PostMapping
    public String saveUserRoles(@RequestParam String username,
                                @RequestParam Map<String, String> form,
                                @RequestParam("userId") User user) {
        userService.saveUser(user, username, form);
        return "redirect:/user";
    }

    @GetMapping("profilePage")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        return "profilePage";
    }

    @PostMapping("profilePage")
    public String updateProfile(@AuthenticationPrincipal User user,
                                @RequestParam String password,
                                @RequestParam String email
    ) {
        userService.updateProfile(user, password, email);
        return "redirect:/user/profilePage";
    }
}