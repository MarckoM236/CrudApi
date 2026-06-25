package com.marco.crudapi.User.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marco.crudapi.User.Dto.RequestUserDto;
import com.marco.crudapi.User.Dto.ResponseUserDto;
import com.marco.crudapi.User.Entity.User;
import com.marco.crudapi.User.Service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @CrossOrigin
    @PostMapping()
    @Operation(
        summary = "Create user API.", 
        description = "Create a user to use the API."
    )
    public ResponseEntity<ResponseUserDto> createUser(@RequestBody RequestUserDto user) {
        User saveUser = userService.createUser(user);

        ResponseUserDto resUser = new ResponseUserDto(
            saveUser.getName(),
            saveUser.getEmail()
        );

        return ResponseEntity.status(201).body(resUser);
    }
    
}
