package com.example.bulletinboard.config;

import com.example.bulletinboard.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable();
        http.authorizeRequests()
                .mvcMatchers("/v1.0/admin/news/waiting/for/admission").hasRole("ADMIN")
                .mvcMatchers("/v1.0/admin/get/all/users").hasRole("ADMIN")
                .mvcMatchers("/v1.0/admin/admit/news/").hasRole("ADMIN")
                .mvcMatchers("/v1.0/admin/reject/news/").hasRole("ADMIN")
                .mvcMatchers("/v1.0/registration", "/v1.0/auth")
                .permitAll()
                .and()
                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest().authenticated();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
