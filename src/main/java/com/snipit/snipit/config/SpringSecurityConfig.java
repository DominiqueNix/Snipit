package com.snipit.snipit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {

    @Autowired
    JwtTokenFilter jwtTokenFilter;

    //hashes password
    @Bean
    static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //filter where you want security
    @Bean 
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        //no auth required for snippers routes or to post a new user
                        .requestMatchers("/snippets", "/snippets/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users", "/users/*").permitAll()
                        //auth reuqired to get users
                        // .requestMatchers(HttpMethod.GET, "/users").authenticated()
                        .requestMatchers(HttpMethod.GET, "/users", "/users/*").permitAll()
                )
                .httpBasic(Customizer.withDefaults());   
        return http.build();
    }

    @Bean
    static UserDetailsService userDetailService() {
        return new InMemoryUserDetailsManager();
    }
}
