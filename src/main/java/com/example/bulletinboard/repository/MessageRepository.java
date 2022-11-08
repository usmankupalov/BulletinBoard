package com.example.bulletinboard.repository;

import com.example.bulletinboard.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    Message findMessageByIdMessage(Integer messageId);
    @Transactional
    void deleteMessageByIdMessage(Integer messageId);
    @Modifying
    @Transactional
    @Query("update Message m " +
            "set m.isActive = ?2 " +
            "where m.idMessage = ?1")
    void updateMessageActive(Integer messageId, boolean isActive);
    @Transactional
    @Query("select m " +
            "from Message m " +
            "where m.isActive=:isActive")
    List<Message> findAllByActive(@Param("isActive") boolean isActive);
    @Modifying
    @Transactional
    @Query("update Message m " +
            "set m.title=:title, m.message=:message, m.dateOfMessagePublished=:date," +
            "m.isActive=:isActive " +
            "where m.idMessage=:messageId")
    void updateMessage(@Param("title") String title, @Param("message") String message,
                       @Param("date") Date date, @Param("isActive") boolean isActive,
                       @Param("messageId") Integer messageId);
    @Transactional
    @Query("select m " +
            "from Message m " +
            "where m.title=:title")
    Message getMessageByTitle(@Param("title") String title);
}
