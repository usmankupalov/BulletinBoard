package com.example.bulletinboard.filter;

import com.example.bulletinboard.model.User;
import com.example.bulletinboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByLogin(username);

        if (user != null) {
            return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
        }
        throw new UsernameNotFoundException("User not found");
    }
}
