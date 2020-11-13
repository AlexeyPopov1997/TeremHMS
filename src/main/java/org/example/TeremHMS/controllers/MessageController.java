package org.example.TeremHMS.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.example.TeremHMS.domain.User;
import org.example.TeremHMS.domain.Message;
import org.example.TeremHMS.repos.MessageRepository;

import java.io.IOException;

@SuppressWarnings("ALL")
@Controller
@RequestMapping("/messagePage")
public class MessageController {
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping
    public String showMessageList(@RequestParam(required = false) String filter, Model model){
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()){
            messages = messageRepository.findByTag(filter);
        }else{
            messages = messageRepository.findAll();
        }
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "messagePage";
    }

    @PostMapping
    public String addNewMessage(@AuthenticationPrincipal User user,
                                @RequestParam String text,
                                @RequestParam String tag, Model model) throws IOException {
        Message message = new Message(text, tag, user);
        message.setStatus("Не принято");
        messageRepository.save(message);
        Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        return "messagePage";
    }

    @GetMapping("{message}")
    public String editMessageStatus(@PathVariable("message") Long id, Model model) {
        Message message =  messageRepository.findById(id);
        message.setStatus("Принято");
        messageRepository.save(message);
        return "redirect:/messagePage";
    }
}