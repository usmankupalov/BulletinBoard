package com.example.bulletinboard.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Integer idMessage;

    @Column(name = "title")
    private String title;

    @Column(name = "message")
    private String message;

    @Column(name = "date_of_message_published")
    private Date dateOfMessagePublished;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public Message() {}

    public Integer getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Integer idMessage) {
        this.idMessage = idMessage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateOfMessagePublished() {
        return dateOfMessagePublished;
    }

    public void setDateOfMessagePublished(Date dateOfMessagePublished) {
        this.dateOfMessagePublished = dateOfMessagePublished;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Message{" +
                "idMessage=" + idMessage +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", datetimeStamp='" + dateOfMessagePublished + '\'' +
                ", isActive='" + isActive + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
