package org.example.TeremHMS.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import org.example.TeremHMS.domain.User;
import org.example.TeremHMS.domain.Message;
import org.example.TeremHMS.repos.MessageRepository;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
                                @Valid Message message,
                                BindingResult bindingResult,
                                Model model) throws IOException {
        message.setAuthor(user);
        message.setStatus("Не принято");
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = UtilsController.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("message" ,message);
        } else {
            model.addAttribute("model", null);
            messageRepository.save(message);
        }
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