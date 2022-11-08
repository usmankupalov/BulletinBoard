package com.example.bulletinboard.service;

import com.example.bulletinboard.model.Message;

import java.util.List;

public interface MessageService {
    List<Message> getAllMessages();
    Message findMessageById(Integer messageId);
    Message getMessageByTitle(String title);
    void addMessage(Message message);
    void admitNews(Integer messageId);
    void rejectNews(Integer messageId);
    List<Message> getAllMessagesByActive(boolean isActive);
    void updateMessage(Integer messageId, Message message);
}
