package com.snipit.snipit.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.Customizer;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.web.SecurityFilterChain;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.security.config.Customizer.withDefaults;

import java.io.IOException;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {

    // @Autowired
    // JwtTokenFilter jwtTokenFilter;

    //hashes password
    // @Bean
    // static PasswordEncoder passwordEncoder(){
    //     return new BCryptPasswordEncoder();
    // }

    //filter where you want security
    // @Bean 
    // SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http
    //             .csrf(csrf -> csrf.disable())
    //             .authorizeHttpRequests(auth -> auth
    //                     //no auth required for snippers routes or to post a new user
    //                     .requestMatchers("/snippets", "/snippets/*").permitAll()
    //                     .requestMatchers(HttpMethod.POST, "/users", "/users/*").permitAll()
    //                     //auth reuqired to get users
    //                     // .requestMatchers(HttpMethod.GET, "/users").authenticated()
    //                     .requestMatchers(HttpMethod.GET, "/users", "/users/*").permitAll()
    //             )
    //             .httpBasic(Customizer.withDefaults());   
    //     return http.build();
    // }

    // @Bean
    // static UserDetailsService userDetailService() {
    //     return new InMemoryUserDetailsManager();
    // }

    //ouath security filter
    @Value("${okta.oauth2.issuer}")
    private String issuer;
    @Value("${okta.oauth2.client-id}")
    private String clientId;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/").permitAll().anyRequest().authenticated()).oauth2Login(withDefaults())
            .logout(logout -> logout.addLogoutHandler(logoutHandler()));
            return http.build();
    }

    private LogoutHandler logoutHandler() {
        return (request, response, authentication) -> {
            try { 
                String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
                response.sendRedirect(issuer + "v2/logout?client_id=" + clientId + "&returnTo=" + baseUrl);
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
