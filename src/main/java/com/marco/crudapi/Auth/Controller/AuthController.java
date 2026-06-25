package com.marco.crudapi.Auth.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marco.crudapi.Auth.Dto.RequestUserAuth;
import com.marco.crudapi.Auth.Dto.SuccessAuthResponse;
import com.marco.crudapi.Auth.Service.AuthService;
import com.marco.crudapi.User.Dto.RequestUserDto;
import com.marco.crudapi.User.Dto.ResponseUserDto;
import com.marco.crudapi.User.Entity.User;
import com.marco.crudapi.User.Service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService service, UserService userService) {
        this.authService = service;
        this.userService = userService;
    }

    @CrossOrigin
    @PostMapping("/login")
    @Operation(
        summary = "Get JWT token.", 
        description = "Validates the user's credentials and returns a JWT token."
    )
    public ResponseEntity<SuccessAuthResponse> login(@RequestBody RequestUserAuth user) {

        String token = authService.login(user);
        SuccessAuthResponse responseAuth = new SuccessAuthResponse(
            token
        );
        
        return ResponseEntity.status(200).body(responseAuth);
    }

    @CrossOrigin
    @PostMapping("/register")
    @Operation(
        summary = "Create user API.", 
        description = "Create a user to use the API."
    )
    public ResponseEntity<ResponseUserDto> registerUser(@RequestBody RequestUserDto user) {
        User saveUser = userService.createUser(user);

        ResponseUserDto resUser = new ResponseUserDto(
            saveUser.getName(),
            saveUser.getEmail()
        );

        return ResponseEntity.status(201).body(resUser);
    }
    
}
