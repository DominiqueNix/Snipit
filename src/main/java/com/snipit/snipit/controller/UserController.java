package com.snipit.snipit.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snipit.snipit.payload.UserDTO;

import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/users")
public class UserController {

    ArrayList<UserDetails> users = new ArrayList<UserDetails>();

    private final PasswordEncoder passwordEncoder;

    public UserController(ApplicationContext context){
        this.passwordEncoder = (PasswordEncoder)context.getBean("passwordEncoder");
    }

    @GetMapping
    public String login(HttpServletRequest req) {
        System.out.println(req.getAuthType());
        return users.get(0).getUsername();
    }


    @PostMapping
    public String register(@RequestBody UserDTO user) {

        UserDetails newUser = User.builder().username(user.getUsername()).password(passwordEncoder.encode(user.getPassword())).roles("USER").build();
        
        users.add(newUser);
        return "Successfully Added";
    }
    
    
    
    
}
