package com.example.bulletinboard.controller;

import com.example.bulletinboard.controller.response.Response;
import com.example.bulletinboard.model.Message;
import com.example.bulletinboard.model.User;
import com.example.bulletinboard.service.MessageService;
import com.example.bulletinboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1.0/user")
public class UserController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @PutMapping(value = "/add/news/")
    public ResponseEntity<?> addNews(@RequestBody Message message,
                                     @RequestParam String login) {
        User user = userService.findUserByLogin(login);
        if (user != null) {
            message.setUser(user);
            messageService.addMessage(message);
            return new ResponseEntity<>(new Response(message.getTitle() + " send to the admin for admission"), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/all/news")
    public ResponseEntity<List<Message>> getAllNewsAdmitted() {
        boolean result = true;
        List<Message> messages = messageService.getAllMessagesByActive(result);
        if (!messages.isEmpty()) {
            return new ResponseEntity<>(messages, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/get/user/news/{login}")
    public ResponseEntity<User> getUserDate(@PathVariable String login) {
        User user = userService.findUserByLogin(login);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/edit/message/{title}")
    public ResponseEntity<?> editMessage(@RequestBody Message newMessage,
                                         @PathVariable String title) {
        Message message = messageService.getMessageByTitle(title);
        if (message != null) {
            messageService.updateMessage(message.getIdMessage(), newMessage);
            return new ResponseEntity<>(new Response(newMessage.getTitle() + " news updated and send to the admin for admission"), HttpStatus.OK)      ;
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
