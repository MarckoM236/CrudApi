package com.marco.crudapi.Auth.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marco.crudapi.Auth.Dto.SuccessAuthResponse;
import com.marco.crudapi.Auth.Service.AuthService;
import com.marco.crudapi.User.Entity.User;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService service) {
        this.authService = service;
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<SuccessAuthResponse> login(@RequestBody User user) {

        String token = authService.login(user);
        SuccessAuthResponse responseAuth = new SuccessAuthResponse(
            token
        );
        
        return ResponseEntity.status(200).body(responseAuth);
    }
    
}
