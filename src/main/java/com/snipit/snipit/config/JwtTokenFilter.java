// package com.snipit.snipit.config;

// import java.io.IOException;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.util.ObjectUtils;
// import org.springframework.web.filter.OncePerRequestFilter;

// import com.snipit.snipit.util.JwtToken;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @Component
// public class JwtTokenFilter extends OncePerRequestFilter{
    
//     @Autowired
//     JwtToken jwtToken;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//         if(!hasAuthorizationBearer(request)) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         String token = getAccessToken(request);

//         if(!jwtToken.validateAccessToken(token)) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         setAuthenticationContent(token, request);
//         filterChain.doFilter(request, response);
//     }

//     private boolean hasAuthorizationBearer(HttpServletRequest request) {
//         String header = request.getHeader("Authorization");
//         if(ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
//             return false;
//         }

//         return true;
//     }

//     private String getAccessToken(HttpServletRequest request) {
//         String header = request.getHeader("Authorization");
//         String token = header.split(" ")[1].trim();
//         return token;
//     }

//     private void setAuthenticationContent(String token, HttpServletRequest request) {
//         UserDetails userDetails = getUserDetails(token);
//         UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, null);
//         authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//         SecurityContextHolder.getContext().setAuthentication(authentication);
//     }

//     private UserDetails getUserDetails(String token) {
//         String[] jwtSubject = jwtToken.getSubject(token).split(",");
//         UserDetails userDetails = User.builder().username(jwtSubject[0]).password(jwtSubject[0]).build();

//         return userDetails;
//     }
// }
