package org.example.TeremHMS.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;

@Controller
public class MainController {
    @GetMapping("/")
    public String openHomePage(Model model) {
        return "homePage";
    }

    @GetMapping("/agreementPage")
    public String openAgreementPage(Model model) {
        return "agreementPage";
    }

    @GetMapping("/profilePage")
    public String openProfilePage(Model model) {
        return "redirect:/user/profilePage";
    }
}