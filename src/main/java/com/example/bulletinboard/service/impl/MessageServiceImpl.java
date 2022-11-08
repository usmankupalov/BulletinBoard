package com.example.bulletinboard.service.impl;

import com.example.bulletinboard.model.Message;
import com.example.bulletinboard.repository.MessageRepository;
import com.example.bulletinboard.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message findMessageById(Integer messageId) {
        return messageRepository.findMessageByIdMessage(messageId);
    }

    @Override
    public Message getMessageByTitle(String title) {
        return messageRepository.getMessageByTitle(title);
    }

    @Override
    public void addMessage(Message message) {
        Date currentDate = new Date();
        message.setDateOfMessagePublished(currentDate);
        messageRepository.save(message);
    }

    @Override
    public void admitNews(Integer messageId) {
        boolean result = true;
        messageRepository.updateMessageActive(messageId, result);
    }

    @Override
    public void rejectNews(Integer messageId) {
        messageRepository.deleteMessageByIdMessage(messageId);
    }

    @Override
    public List<Message> getAllMessagesByActive(boolean isActive) {
        return messageRepository.findAllByActive(isActive);
    }

    @Override
    public void updateMessage(Integer messageId, Message message) {
        Date date = new Date();
        boolean isActive = false;
        message.setDateOfMessagePublished(date);
        message.setActive(isActive);
        messageRepository.updateMessage(message.getTitle(), message.getMessage(), date, isActive, messageId);
    }
}
