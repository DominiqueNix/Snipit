package com.snipit.snipit.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snipit.snipit.payload.UserDTO;
// import com.snipit.snipit.util.JwtToken;

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

    // @Autowired
    // JwtToken jwtToken;
    private final PasswordEncoder passwordEncoder;
    public UserController(ApplicationContext context){
        this.passwordEncoder = (PasswordEncoder)context.getBean("passwordEncoder");
        UserDetails mike = User.builder().username("mike").password(passwordEncoder.encode("testing")).roles("USER").build();
        users.add(mike);
    }

    // @GetMapping
    // public String login(HttpServletRequest req) {
    //     // System.out.println(req.getAuthType());
    //     // String result = users.get(0).getUsername() + " " + users.get(0).getPassword() + " " + users.get(1).getUsername() + " " + users.get(1).getPassword() ;
    //     return (String) req.getAttribute("username");
    // }

    @GetMapping
    public String getUsers() {
        String result = "";
        for(UserDetails user : users) {
            result += "username: " + user.getUsername() + " password: " + user.getPassword();
        }

        return result;
    }

    @GetMapping("/login")
    public String login(@RequestBody UserDTO userData) {
        
        ArrayList<Boolean> booleans = new ArrayList<>();

        UserDetails user;

        for(UserDetails u : users) {
            if(!u.getUsername().equals(userData.getUsername())) {
                booleans.add(false);
            }else {
                booleans.add(true);
                // user = User.builder().username(u.getUsername()).password(passwordEncoder.encode(u.getPassword())).roles("USER").build();
            }
        }

        if(!booleans.contains(true)) {
            return "User not found";
        }
        
        
        
    }
    


    @PostMapping
    public String register(@RequestBody UserDTO user) {

        UserDetails newUser = User.builder().username(user.getUsername()).password(passwordEncoder.encode(user.getPassword())).roles("USER").build();
        
        users.add(newUser);
        return "Successfully Added";
    }
    
    
    
    
}
