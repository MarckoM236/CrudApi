package com.marco.crudapi.Auth.Filter;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.marco.crudapi.Auth.Service.JwtService;
import com.marco.crudapi.User.Entity.User;
import com.marco.crudapi.User.Service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

    private final JwtService jwtService;
    private final UserService userService;

    public JwtFilter(JwtService jwtService, UserService userService){
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain) throws ServletException, IOException {

        String token = this.getToken(request);

        if(token != null && !token.isEmpty()){
            String userEmail = jwtService.extractUsername(token);

            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                Optional<User> userDetails = userService.userByEmail(userEmail);

                if(userDetails.isPresent()){
                    boolean tokenValid = jwtService.isTokenValid(token, userDetails.get().getEmail());
                    if(tokenValid){
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails.get(), null, null);
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            }
            
        }

        filterChain.doFilter(request, response);
    }

    //get only string token
    private String getToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");

        if(header == null){
            return null;
        }

        String token = header.substring(7);
        
        return token;
    }
    
}
