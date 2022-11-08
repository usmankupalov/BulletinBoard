package com.example.bulletinboard.controller;

import com.example.bulletinboard.controller.response.Response;
import com.example.bulletinboard.model.Message;
import com.example.bulletinboard.model.User;
import com.example.bulletinboard.service.MessageService;
import com.example.bulletinboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1.0/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/get/all/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/news/waiting/for/admission")
    public ResponseEntity<List<Message>> getNewsForAdmission() {
        boolean result = false;
        List<Message> messages = messageService.getAllMessagesByActive(result);
        if (!messages.isEmpty()) {
            return new ResponseEntity<>(messages, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/admit/news/{messageId}")
    public ResponseEntity<?> admitNews(@PathVariable(required = true) Integer messageId) {
        Message message = messageService.findMessageById(messageId);
        if (message != null) {
            messageService.admitNews(messageId);
            return new ResponseEntity<>(new Response("Admin admitted news being uploaded to the main page"), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/reject/news/{messageId}")
    public ResponseEntity<?> rejectNews(@PathVariable Integer messageId) {
        Message message = messageService.findMessageById(messageId);
        if (message != null) {
            messageService.rejectNews(messageId);
            return new ResponseEntity<>(new Response("Admin rejected news"), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
