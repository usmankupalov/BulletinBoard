package com.example.bulletinboard.config;

import com.example.bulletinboard.repository.MessageRepository;
import com.example.bulletinboard.repository.RoleRepository;
import com.example.bulletinboard.repository.UserRepository;
import com.example.bulletinboard.service.MessageService;
import com.example.bulletinboard.service.UserService;
import com.example.bulletinboard.service.impl.MessageServiceImpl;
import com.example.bulletinboard.service.impl.UserServiceImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableConfigurationProperties
public class BulletinConfig {

    @Bean
    public UserService userService(UserRepository userRepository, RoleRepository roleRepository) {
        return new UserServiceImpl(userRepository, roleRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MessageService messageService(MessageRepository messageRepository) {
        return new MessageServiceImpl(messageRepository);
    }
}
